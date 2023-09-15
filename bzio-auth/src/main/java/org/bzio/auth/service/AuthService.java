package org.bzio.auth.service;

import org.bzio.common.security.entity.LoginUser;
import org.bzio.common.security.entity.MenuTreeNode;
import org.bzio.common.security.entity.SysUser;

import java.util.List;
import java.util.Map;

/**
 * @author snow
 */
public interface AuthService {

    LoginUser login(SysUser sysUser);

    int register(SysUser sysUser);

    int updatePassword(String username, String password, String newPassword);

    boolean isLogin(String username);

    boolean force(String username, String password);

    String getKey(String username);
    
    Map<String, Object> getInfo(String userId);
    
    List<MenuTreeNode> getRouter();
}
