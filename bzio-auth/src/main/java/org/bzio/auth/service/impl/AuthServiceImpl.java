package org.bzio.auth.service.impl;

import org.bzio.auth.service.AuthService;
import org.bzio.common.core.config.AuthConfig;
import org.bzio.common.core.config.BaseConstant;
import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.IdUtil;
import org.bzio.common.core.util.JwtUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.redis.service.StringRedisService;
import org.bzio.common.security.entity.LoginUser;
import org.bzio.common.security.service.impl.UserDetailsServiceImpl;
import org.bzio.system.entity.SysUser;
import org.bzio.system.mapper.SysUserMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * 认证业务服务层
 *
 * @author: snow
 */
@Service
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {

    @Resource
    JwtUtil jwtUtil;
    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    StringRedisService stringRedisService;
    @Resource
    UserDetailsServiceImpl userDetailsService;
    @Resource
    SysUserMapper sysUserMapper;

    /**
     * 用户登录
     */
    @Override
    public String login(SysUser sysUser) {
        // 用户名为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getUserName())) {
            log.error("用户名不能为空！");
            throw new UserException("用户名不能为空！");
        }
        // 密码为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getPassword())) {
            log.error("密码不能为空！");
            throw new UserException("密码不能为空！");
        }
        LoginUser loginUser = (LoginUser) userDetailsService.loadUserByUsername(sysUser.getUserName());
        // 用户不存在，抛出异常
        if (loginUser == null) {
            log.error("用户名不存在！");
            throw new UserException("用户名不存在！");
        }
        // 密码错误，抛出异常
        if (!bCryptPasswordEncoder.matches(sysUser.getPassword(), loginUser.getPassword())) {
            log.error("密码错误！");
            throw new UserException("密码错误！");
        }

        log.info("登录时间：{}", loginUser.getLoginTime());
        // 生成token
        String key = loginUser.getUserId() + "_" +  DateUtil.format(loginUser.getLoginTime(), BaseConstant.YYYYMMDDHHMMSS);
        log.info("生成key：{}", key);
        String token = jwtUtil.generateToken(sysUser.getUserName());

        // redis缓存token
        stringRedisService.set(AuthConfig.prefix + key, token, AuthConfig.expiration, TimeUnit.MILLISECONDS);

        // 修改最后登录的信息
        sysUserMapper.updateLoginInfo(loginUser.getIpaddr(), loginUser.getLoginTime(), loginUser.getUserId());

        // 添加用户登录信息
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        log.info("authenticated user:{}", loginUser.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("登录成功！");
        return key;
    }

    /**
     * 用户注册
     */
    @Override
    public int register(SysUser sysUser) {
        // 用户名为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getUserName())) {
            log.error("用户名不能为空！");
            throw new UserException("用户名不能为空！");
        }
        // 密码为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getPassword())) {
            log.error("密码不能为空！");
            throw new UserException("密码不能为空！");
        }
        SysUser user = sysUserMapper.queryByUserName(sysUser.getUserName());
        // 用户名存在，抛出异常
        if (user != null && sysUser.getUserName().equals(user.getUserName())) {
            log.error("用户名已存在！");
            throw new UserException("用户名已存在，不能重复！");
        }

        // 新增用户
        sysUser.setUserId(IdUtil.simpleUUID());
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public int updatePassword(String userName, String password) {
        SysUser sysUser = sysUserMapper.queryByUserName(userName);
        sysUser.setPassword(bCryptPasswordEncoder.encode(password));
        return sysUserMapper.update(sysUser);
    }

    @Override
    public boolean isLogin(String userName) {
        String key = getToken(userName);
        String token = stringRedisService.get(key);
        return StringUtil.isNotEmpty(token);
    }

    @Override
    public String getToken(String userName) {
        SysUser user = sysUserMapper.queryByUserName(userName);
        String key = AuthConfig.prefix + user.getUserId() + "_" + DateUtil.format(user.getLoginDate(), BaseConstant.YYYYMMDDHHMMSS);
        return key;
    }
}
