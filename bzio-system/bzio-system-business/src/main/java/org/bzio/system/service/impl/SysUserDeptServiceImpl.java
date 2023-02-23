package org.bzio.system.service.impl;

import org.bzio.common.core.util.IdUtil;
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
        sysUserDept.setId(IdUtil.simpleUUID());
        return sysUserDeptMapper.insert(sysUserDept);
    }
}
