package org.bzio.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bzio.system.entity.SysUser;
import org.bzio.system.qo.SysUserQo;

import java.util.Date;
import java.util.List;

/**
 * @author: snow
 */
@Mapper
public interface SysUserMapper {

    SysUser queryByUserId(String userId);

    SysUser queryByUserName(String userName);

    List<SysUser> queryAll(SysUserQo sysUser);

    int insert(SysUser sysUser);

    int update(SysUser sysUser);

    int updateLoginInfo(@Param("loginIp") String loginIp, @Param("loginDate") Date loginDate, @Param("userId") String userId);

    int deleteByUserName(String userName);
}
