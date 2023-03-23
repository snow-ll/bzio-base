package org.bzio.system.service;

import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.qo.SysUserQo;

import java.util.List;

/**
 * @author: snow
 */
public interface SysUserService {

    SysUser queryInfo(String username);

    List<SysUser> queryAll(SysUserQo sysUser);

    int saveUser(SysUser sysUser);

    int deleteUser(String username);
}
