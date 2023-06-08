package org.bzio.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.entity.SysUserRole;
import org.bzio.common.security.qo.SysUserQo;

import java.util.List;

/**
 * @author: snow
 */
@Mapper
public interface SysUserRoleMapper {

    boolean queryHasRole(SysUserRole sysUserRole);

    int insert(SysUserRole sysUserRole);

    int insertBatch(@Param("sysUserRoles") List<SysUserRole> sysUserRoles);

    int delete(SysUserRole sysUserRole);

    int deleteByUserId(String userId);
    
    List<SysUser> queryUserByRole(SysUserQo sysUserQo);
}
