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

    /**
     * 用户登录
     */
    LoginUser login(SysUser sysUser);

    /**
     * 用户注册
     */
    int register(SysUser sysUser);

    /**
     * 修改密码
     */
    int updatePassword(String username, String password, String newPassword);

    /**
     * 是否登录状态
     */
    boolean isLogin(String username);

    /**
     * 强制注销
     */
    boolean force(String username, String password);

    /**
     * 生成存入登录用户信息redis的key
     */
    String generateKey(String username);

    /**
     * 获取登录用户信息（用户信息、角色、权限）
     */
    Map<String, Object> getInfo(String userId);

    /**
     * 获取用户的路由
     */
    List<MenuTreeNode> getRouter();
}
