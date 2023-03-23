package org.bzio.common.security.util;

import org.bzio.common.core.util.StringUtil;
import org.bzio.common.security.entity.LoginUser;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 认证、权限工具类
 *
 * @author: snow
 */
public class AuthUtil {

    public static String getUsername() {
        return getLoginUser().getUsername();
    }

    public static String getNickname() {
        return getLoginUser().getNickname();
    }

    private static LoginUser getLoginUser() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (StringUtil.compareStr("anonymousUser", o)) {
            return new LoginUser();
        } else {
            return (LoginUser) o;
        }
    }
}
