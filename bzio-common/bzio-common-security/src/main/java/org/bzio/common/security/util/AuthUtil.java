package org.bzio.common.security.util;

import org.bzio.common.security.entity.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 认证、权限工具类
 *
 * @author: snow
 */
public class AuthUtil {

    public static String getUserName() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String userName = auth.getName();

        // 获取登录用户信息
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return user.getUsername();
    }

    public static String getNickName() {
        LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getNickName();
    }
}
