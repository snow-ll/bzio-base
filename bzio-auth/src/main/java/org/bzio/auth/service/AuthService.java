package org.bzio.auth.service;

import org.bzio.common.security.entity.LoginUser;
import org.bzio.system.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: snow
 */
public interface AuthService {

    LoginUser login(SysUser sysUser);

    int register(SysUser sysUser);

    int updatePassword(String userName, String password);
}
