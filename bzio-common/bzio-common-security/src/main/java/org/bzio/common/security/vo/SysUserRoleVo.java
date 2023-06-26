package org.bzio.common.security.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author snow
 */
public class SysUserRoleVo implements Serializable {
    
    private static final long serialVersionUID = -1266094222353158691L;

    private String id;

    private String userId;

    private String roleId;
    
    private List<String> userIds;

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

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }
}
