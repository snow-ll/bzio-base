package org.bzio.auth.service;

import org.bzio.common.security.entity.SysUser;

/**
 * @author: snow
 */
public interface AuthService {

    String login(SysUser sysUser);

    int register(SysUser sysUser);

    int updatePassword(String username, String password, String newPassword);

    boolean isLogin(String username);

    boolean force(String username, String password);

    String getKey(String username);
}
