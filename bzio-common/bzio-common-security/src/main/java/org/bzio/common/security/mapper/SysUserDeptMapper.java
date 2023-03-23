package org.bzio.common.security.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.bzio.common.security.entity.SysUserDept;

/**
 * @author: snow
 */
@Mapper
public interface SysUserDeptMapper {

    int insert(SysUserDept sysUserDept);
}
