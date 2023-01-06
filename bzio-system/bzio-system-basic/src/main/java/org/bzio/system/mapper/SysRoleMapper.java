package org.bzio.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bzio.system.entity.SysRole;

import java.util.List;

/**
 * @author: snow
 */
@Mapper
public interface SysRoleMapper {
    SysRole queryById(String roleId);

    List<SysRole> queryAll(SysRole sysRole);

    List<String> queryRoleByUserId(String userId);

    int insert(SysRole sysRole);

    int update(SysRole sysRole);

    int deleteById(String roleId);
}