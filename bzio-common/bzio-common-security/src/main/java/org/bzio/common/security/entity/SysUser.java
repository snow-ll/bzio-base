package org.bzio.common.security.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.bzio.common.core.web.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户实体类
 *
 * @author snow
 */
@Data
public class SysUser extends BaseEntity {

    private static final long serialVersionUID = -2916141177184809068L;

    /**
     * 用户id
     */
    private String userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 出生日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date birthday;
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
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginDate;

    /**
     * 部门id
     */
    private String deptId;
}
