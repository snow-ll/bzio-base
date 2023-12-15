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

    /**
     * 根绝角色id查询用户信息
     */
    List<SysUser> queryUserByRole(SysUserQo sysUserQo);

    /**
     * 批量保存用户角色关联信息
     */
    int saveUserRole(List<SysUserRole> sysUserRoles);

    /**
     * 授权用户权限
     */
    int authUser(SysUserRoleVo sysUserRoleVo);

    /**
     * 取消用户授权
     */
    int cancel(SysUserRole sysUserRole);
}
