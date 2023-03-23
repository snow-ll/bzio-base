package org.bzio.common.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 登录用户信息
 *
 * @author: snow
 */
public class LoginUser extends User {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    public LoginUser() {
        super("anonymousUser", "", new ArrayList<>());
    }

    public LoginUser(String userId, String username, String password, String nickname, Date loginTime, String ipaddr, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userId = userId;
        this.nickname = nickname;
        this.loginTime = loginTime;
        this.ipaddr = ipaddr;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }
}
