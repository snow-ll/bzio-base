package org.bzio.system.service;


import org.bzio.system.entity.SysRole;

import java.util.List;

/**
 * @author: snow
 */
public interface SysRoleService {

    SysRole queryInfo(String roleId);

    List<SysRole> queryAll(SysRole sysRole);

    int saveRole(SysRole sysRole);

    int deleteRole(String roleId);
}
