package org.bzio.system.service;


import org.bzio.common.security.entity.SysUserRole;

import java.util.List;

/**
 * @author: snow
 */
public interface SysUserRoleService {

    int saveUserRole(List<SysUserRole> sysUserRoles);
}
