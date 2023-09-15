package org.bzio.common.security.entity;

import java.io.Serializable;

/**
 * 系统用户部门关联表
 *
 * @author snow
 */
public class SysUserDept implements Serializable {

    private static final long serialVersionUID = 6859082493603128522L;

    private String id;

    private String userId;

    private String deptId;

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

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }
}
