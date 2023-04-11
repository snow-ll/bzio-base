package org.bzio.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.qo.SysUserQo;

import java.util.Date;
import java.util.List;

/**
 * @author: snow
 */
@Mapper
public interface SysUserMapper {

    SysUser queryByUserId(String userId);

    SysUser queryByUsername(String username);

    List<SysUser> queryAll(SysUserQo sysUser);

    int insert(SysUser sysUser);

    int update(SysUser sysUser);

    int updateLoginInfo(@Param("loginIp") String loginIp, @Param("loginDate") Date loginDate, @Param("userId") String userId);

    int deleteByUsername(String username);
}