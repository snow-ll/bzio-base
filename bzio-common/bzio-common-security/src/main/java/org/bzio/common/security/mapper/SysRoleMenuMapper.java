package org.bzio.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bzio.common.security.entity.SysRoleMenu;

import java.util.List;

/**
 * @author snow
 */
@Mapper
public interface SysRoleMenuMapper {

    List<String> queryMenuIdsByRoleId(String roleId);
    
    int insert(SysRoleMenu sysRoleMenu);
    
    int delete(SysRoleMenu sysRoleMenu);
    
    int deleteByRoleId(String roleId);
}
