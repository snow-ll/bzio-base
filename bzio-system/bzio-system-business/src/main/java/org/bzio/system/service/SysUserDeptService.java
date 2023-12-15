package org.bzio.system.service;

import org.bzio.common.security.entity.SysUserDept;

/**
 * @author snow
 */
public interface SysUserDeptService {

    /**
     * 用户添加到部门
     */
    int saveUserDept(SysUserDept sysUserDept);
}
