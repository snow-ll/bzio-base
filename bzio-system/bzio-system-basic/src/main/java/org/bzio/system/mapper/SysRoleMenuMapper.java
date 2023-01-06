package org.bzio.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bzio.system.entity.SysRoleMenu;

/**
 * @author: snow
 */
@Mapper
public interface SysRoleMenuMapper {

    int insert(SysRoleMenu sysRoleMenu);

    int delete(SysRoleMenu sysRoleMenu);
}
