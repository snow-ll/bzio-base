package org.bzio.system.service;

import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.qo.SysUserQo;
import org.bzio.common.security.bo.SysUserBo;

import java.util.List;

/**
 * @author snow
 */
public interface SysUserService {

    /**
     * 根据用户名查询用户详情
     */
    SysUser queryInfo(String userId);

    /**
     * 根据条件查询用户列表
     */
    List<SysUserBo> queryAll(SysUserQo sysUser);

    /**
     * 新增用户
     */
    int saveUser(SysUser sysUser);

    /**
     * 修改用户状态
     * @param userId
     * @param status
     * @return
     */
    int changeStatus(String userId, Integer status);

    /**
     * 根据用户名删除用户
     */
    int deleteUser(String username);
}
