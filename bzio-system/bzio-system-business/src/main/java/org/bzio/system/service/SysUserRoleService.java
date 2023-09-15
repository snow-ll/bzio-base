package org.bzio.system.service;


import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.entity.SysUserRole;
import org.bzio.common.security.qo.SysUserQo;
import org.bzio.common.security.vo.SysUserRoleVo;

import java.util.List;

/**
 * @author snow
 */
public interface SysUserRoleService {

    int saveUserRole(List<SysUserRole> sysUserRoles);
    
    int authUser(SysUserRoleVo sysUserRoleVo);
    
    List<SysUser> queryUserByRole(SysUserQo sysUserQo);
    
    int cancel(SysUserRole sysUserRole);
}
