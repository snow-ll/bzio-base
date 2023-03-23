package org.bzio.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bzio.common.security.entity.SysUserRole;

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
}
