package org.bzio.system.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.bzio.system.entity.SysUserDept;

/**
 * @author: snow
 */
@Mapper
public interface SysUserDeptMapper {

    int insert(SysUserDept sysUserDept);
}
