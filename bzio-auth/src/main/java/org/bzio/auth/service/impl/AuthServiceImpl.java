package org.bzio.auth.service.impl;

import org.bzio.auth.service.AuthService;
import org.bzio.common.core.config.AuthConfig;
import org.bzio.common.core.config.BaseConstant;
import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.*;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.redis.service.StringRedisService;
import org.bzio.common.security.entity.LoginUser;
import org.bzio.common.security.entity.MenuTreeNode;
import org.bzio.common.security.entity.SysMenu;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.mapper.SysMenuMapper;
import org.bzio.common.security.mapper.SysRoleMapper;
import org.bzio.common.security.mapper.SysUserMapper;
import org.bzio.common.security.qo.SysMenuQo;
import org.bzio.common.security.service.impl.UserDetailsServiceImpl;
import org.bzio.common.security.util.AuthUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 认证业务服务层
 *
 * @author snow
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
    @Resource
    SysRoleMapper sysRoleMapper;
    @Resource
    SysMenuMapper sysMenuMapper;

    /**
     * 用户登录
     */
    @Override
    public LoginUser login(SysUser sysUser) {
        // 用户名为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getUsername())) {
            log.error("用户名不能为空！");
            throw new UserException("用户名不能为空！");
        }
        // 密码为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getPassword())) {
            log.error("密码不能为空！");
            throw new UserException("密码不能为空！");
        }
        LoginUser loginUser = (LoginUser) userDetailsService.loadUserByUsername(sysUser.getUsername());
        // 用户不存在，抛出异常
        if (loginUser == null) {
            log.error("用户不存在！");
            throw new UserException("用户不存在！");
        }
        // 密码错误，抛出异常
        if (!bCryptPasswordEncoder.matches(sysUser.getPassword(), loginUser.getPassword())) {
            log.error("密码错误！");
            throw new UserException("密码错误！");
        }
        if (loginUser.getStatus() != 0) {
            log.error("用户被停用！");
            throw new UserException("用户被停用！");
        }

        Date loginTime = DateUtil.getNowDateToSeconds();
        log.info("登录时间：{}", DateUtil.format(loginTime, BaseConstant.YYYY_MM_DD_HH_MM_SS));
        // 生成token
        String key = loginUser.getUserId() + "_" +  DateUtil.format(loginTime, BaseConstant.YYYYMMDDHHMMSS);
        log.info("生成key：{}", key);
        String token = jwtUtil.generateToken(sysUser.getUsername());
        loginUser.setKey(key);

        // redis缓存token
        stringRedisService.set(AuthConfig.prefix + key, token, AuthConfig.expiration, TimeUnit.MILLISECONDS);

        // 修改最后登录的信息
        sysUserMapper.updateLoginInfo(ServletUtil.getIpAddr(), loginTime, loginUser.getUserId());

        // 添加用户登录信息
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        log.info("authenticated user:{}", loginUser.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("登录成功！");
        return loginUser;
    }

    /**
     * 用户注册
     */
    @Override
    public int register(SysUser sysUser) {
        // 用户名为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getUsername())) {
            log.error("用户名不能为空！");
            throw new UserException("用户名不能为空！");
        }
        // 密码为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getPassword())) {
            log.error("密码不能为空！");
            throw new UserException("密码不能为空！");
        }
        SysUser user = sysUserMapper.queryByUsername(sysUser.getUsername());
        // 用户名存在，抛出异常
        if (user != null && sysUser.getUsername().equals(user.getUsername())) {
            log.error("用户名已存在！");
            throw new UserException("用户名已存在，不能重复！");
        }

        // 新增用户
        sysUser.setUserId(IdUtil.simpleUUID());
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        sysUser.setStatus(0);
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public int updatePassword(String username, String password, String newPassword) {
        if (StringUtil.isEmpty(username)) throw new UserException("用户名不能为空！");
        if (StringUtil.isEmpty(password)) throw new UserException("原密码不能为空！");
        if (StringUtil.isEmpty(newPassword)) throw new UserException("新密码不能为空！");

        SysUser sysUser = sysUserMapper.queryByUsername(username);
        // 验证用户名密码是否正确
        if (sysUser == null) throw new UserException("用户不存在！");
        if (!bCryptPasswordEncoder.matches(password, sysUser.getPassword())) throw new UserException("原密码不正确！");

        // 修改密码
        sysUser.setPassword(bCryptPasswordEncoder.encode(newPassword));
        return sysUserMapper.update(sysUser);
    }

    @Override
    public boolean isLogin(String username) {
        String key = getKey(username);
        String token = stringRedisService.get(key);
        return StringUtil.isNotEmpty(token);
    }

    @Override
    public boolean force(String username, String password) {
        // 验证用户名密码是否正确
        if (verifyUser(username, password)) {
            // 验证信息成功删除登录信息
            String key = getKey(username);
            return stringRedisService.delete(key);
        } else {
            return false;
        }
    }

    @Override
    public String getKey(String username) {
        SysUser user = sysUserMapper.queryByUsername(username);
        return AuthConfig.prefix + user.getUserId() + "_" + DateUtil.format(user.getLoginDate(), BaseConstant.YYYYMMDDHHMMSS);
    }

    private boolean verifyUser(String username, String password) {
        // 用户名为空，抛出异常
        if (StringUtil.isEmpty(username)) {
            log.error("用户名不能为空！");
            return false;
        }
        SysUser sysUser = sysUserMapper.queryByUsername(username);
        // 用户不存在，抛出异常
        if (sysUser == null) {
            log.error("用户不存在！");
            return false;
        }
        // 密码错误，抛出异常
        if (!bCryptPasswordEncoder.matches(password, sysUser.getPassword())) {
            log.error("密码错误！");
            return false;
        }
        return true;
    }

    /**
     * 获取登录用户信息（用户信息、角色、权限）
     */
    @Override
    public Map<String, Object> getInfo(String userId) {
        Map<String, Object> userInfo = new HashMap();
        userInfo.put("user", sysUserMapper.queryByUserId(userId));
        userInfo.put("roles", sysRoleMapper.queryRoleByUserId(userId));
        userInfo.put("perms", sysMenuMapper.queryPermByUserId(userId).stream().filter(item -> StringUtil.isNotEmpty(item)).collect(Collectors.toList()));
        return userInfo;
    }

    @Override
    public List<MenuTreeNode> getRouter() {
        return TreeNodeUtil.buildTreeList((sysMenuMapper.queryRouter(AuthUtil.getUserId())));
    }
}
