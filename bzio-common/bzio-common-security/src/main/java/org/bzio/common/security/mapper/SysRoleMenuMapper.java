package org.bzio.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bzio.common.security.entity.SysRoleMenu;

/**
 * @author: snow
 */
@Mapper
public interface SysRoleMenuMapper {

    int insert(SysRoleMenu sysRoleMenu);

    int delete(SysRoleMenu sysRoleMenu);
}
