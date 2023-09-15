package org.bzio.common.security.entity;

import java.io.Serializable;

/**
 * 用户角色关联表实体
 *
 * @author snow
 */
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 5497130758141266081L;

    private String id;

    private String userId;

    private String roleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
