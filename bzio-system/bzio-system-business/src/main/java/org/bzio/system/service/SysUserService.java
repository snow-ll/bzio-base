package org.bzio.system.service;

import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.qo.SysUserQo;
import org.bzio.common.security.vo.SysUserVo;

import java.util.List;

/**
 * @author: snow
 */
public interface SysUserService {

    SysUser queryInfo(String userId);

    List<SysUserVo> queryAll(SysUserQo sysUser);

    int saveUser(SysUser sysUser);

    int deleteUser(String username);
    
    int changeStatus(String userId, Integer status);
}
