package org.bzio.system.entity;

/**
 * 角色菜单关联表实体
 *
 * @author: snow
 */
public class SysRoleMenu {

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
