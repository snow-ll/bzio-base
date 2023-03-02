package org.bzio.auth.service;

import org.bzio.system.entity.SysUser;

/**
 * @author: snow
 */
public interface AuthService {

    String login(SysUser sysUser);

    int register(SysUser sysUser);

    int updatePassword(String userName, String password, String newPassword);

    boolean isLogin(String userName);

    boolean force(String userName, String password);

    String getKey(String userName);
}
