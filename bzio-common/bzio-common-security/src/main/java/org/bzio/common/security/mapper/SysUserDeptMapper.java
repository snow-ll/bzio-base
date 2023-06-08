package org.bzio.common.security.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bzio.common.security.entity.SysUserDept;

/**
 * @author: snow
 */
@Mapper
public interface SysUserDeptMapper {
    
    SysUserDept queryByUserId(String userId);
    
    int insert(SysUserDept sysUserDept);
    
    int delete(SysUserDept sysUserDept);
}
