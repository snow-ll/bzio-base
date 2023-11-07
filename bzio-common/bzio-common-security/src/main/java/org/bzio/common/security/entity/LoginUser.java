package org.bzio.common.security.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.bzio.common.core.util.BeanUtil;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * 登录用户信息
 *
 * @author snow
 */
@Data
public class LoginUser extends User implements Serializable {

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户名
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 出生日期
     */
    private String birthday;
    /**
     * 用户性别（0男 1女）
     */
    private Integer sex;
    /**
     * 证件类型
     */
    private String certificateType;
    /**
     * 证件号
     */
    private String certificateNum;
    /**
     * 手机号码
     */
    private String mobileNumber;
    /**
     * 电话号码
     */
    private String phoneNumber;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 传真
     */
    private String fax;
    /**
     * 头像路径
     */
    private String avatar;
    /**
     * 帐号状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;
    /**
     * 登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginDate;
    /**
     * 登录IP地址
     */
    private String ipaddr;
    /**
     * 认证key
     */
    private String key;

    public LoginUser() {
        super("anonymousUser", "", new ArrayList<>());
    }

    public LoginUser(SysUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        BeanUtil.copyPropertiesIgnoreNull(user, this);
    }
}
