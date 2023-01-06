package org.bzio.auth.service.impl;

import org.bzio.auth.service.AuthService;
import org.bzio.common.core.config.AuthConfig;
import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.IDUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.LoginUser;
import org.bzio.common.security.service.impl.UserDetailsServiceImpl;
import org.bzio.system.entity.SysUser;
import org.bzio.system.mapper.SysUserMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 认证业务服务层
 *
 * @author: snow
 */
@Service
public class AuthServiceImpl extends BaseServiceImpl implements AuthService {

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    UserDetailsServiceImpl userDetailsService;
    @Resource
    SysUserMapper sysUserMapper;

    /**
     * 用户登录
     */
    @Override
    public LoginUser login(SysUser sysUser) {
        // 用户名为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getUserName())) {
            logger.error("用户名不能为空！");
            throw new UserException("用户名不能为空！");
        }
        // 密码为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getPassword())) {
            logger.error("密码不能为空！");
            throw new UserException("密码不能为空！");
        }
        LoginUser user = (LoginUser) userDetailsService.loadUserByUsername(sysUser.getUserName());
        // 用户不存在，抛出异常
        if (user == null) {
            logger.error("用户名不存在！");
            throw new UserException("用户名不存在！");
        }
        // 密码错误，抛出异常
        if (!bCryptPasswordEncoder.matches(sysUser.getPassword(), user.getPassword())) {
            logger.error("密码错误！");
            throw new UserException("密码错误！");
        }
        return user;
    }

    /**
     * 用户注册
     */
    @Override
    public int register(SysUser sysUser) {
        // 用户名为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getUserName())) {
            logger.error("用户名不能为空！");
            throw new UserException("用户名不能为空！");
        }
        // 密码为空，抛出异常
        if (StringUtil.isEmpty(sysUser.getPassword())) {
            logger.error("密码不能为空！");
            throw new UserException("密码不能为空！");
        }
        SysUser user = sysUserMapper.queryByUserName(sysUser.getUserName());
        // 用户名存在，抛出异常
        if (user != null && sysUser.getUserName().equals(user.getUserName())) {
            logger.error("用户名已存在！");
            throw new UserException("用户名已存在，不能重复！");
        }

        // 新增用户
        sysUser.setUserId(IDUtil.simpleUUID());
        sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public int updatePassword(String userName, String password) {
        SysUser sysUser = sysUserMapper.queryByUserName(userName);
        sysUser.setPassword(bCryptPasswordEncoder.encode(password));
        return sysUserMapper.update(sysUser);
    }
}
