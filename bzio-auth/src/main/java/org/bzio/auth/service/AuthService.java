package org.bzio.auth.service;

import org.bzio.common.security.entity.LoginUser;
import org.bzio.system.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: snow
 */
public interface AuthService {

    String login(SysUser sysUser);

    int register(SysUser sysUser);

    int updatePassword(String userName, String password);

    boolean isLogin(String userName);

    String getToken(String userName);
}
