package org.bzio.common.security.entity;

import java.io.Serializable;

/**
 * 角色菜单关联表实体
 *
 * @author: snow
 */
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = -8753494836637008114L;

    private String id;

    private String roleId;

    private String menuId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}
