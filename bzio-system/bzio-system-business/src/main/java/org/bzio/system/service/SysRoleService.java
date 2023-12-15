package org.bzio.system.service;


import org.bzio.common.security.entity.SysRole;

import java.util.List;

/**
 * @author snow
 */
public interface SysRoleService {

    /**
     * 角色详情信息
     */
    SysRole queryInfo(String roleId);

    /**
     * 角色列表查询
     */
    List<SysRole> queryAll(SysRole sysRole);

    /**
     * 保存角色
     */
    int saveRole(SysRole sysRole);

    /**
     * 删除角色
     */
    int deleteRole(String roleId);
}
