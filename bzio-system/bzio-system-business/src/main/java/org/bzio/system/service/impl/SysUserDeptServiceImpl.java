package org.bzio.system.service.impl;

import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.IDUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.system.entity.SysUserDept;
import org.bzio.system.mapper.SysUserDeptMapper;
import org.bzio.system.service.SysUserDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: snow
 */
@Service
public class SysUserDeptServiceImpl extends BaseServiceImpl implements SysUserDeptService {

    @Resource
    SysUserDeptMapper sysUserDeptMapper;

    /**
     * 用户添加到部门
     */
    @Override
    public int saveUserDept(SysUserDept sysUserDept) {
        sysUserDept.setId(IDUtil.simpleUUID());
        return sysUserDeptMapper.insert(sysUserDept);
    }
}
