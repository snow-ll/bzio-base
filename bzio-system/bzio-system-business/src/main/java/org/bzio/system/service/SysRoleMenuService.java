package org.bzio.system.service;

import java.util.List;

/**
 * @author snow
 */
public interface SysRoleMenuService {

    /**
     * 获取菜单id
     */
    List<String> queryMenuIdsByRoleId(String roleId);

    /**
     * 批量给角色添加菜单访问权限
     * @param roleId 添加菜单角色
     * @param menuIds 添加菜单的id
     * @return
     */
    int insertBatch(String roleId, List<String> menuIds);

    /**
     * 清空原有的菜单权限
     */
    int clearMenu(String roleId);
}
