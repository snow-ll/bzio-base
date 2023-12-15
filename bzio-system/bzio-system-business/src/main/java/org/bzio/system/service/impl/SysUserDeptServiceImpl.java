package org.bzio.system.service.impl;

import org.bzio.common.core.util.snowflake.SnowflakeIdGenerator;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.SysUserDept;
import org.bzio.common.security.mapper.SysUserDeptMapper;
import org.bzio.system.service.SysUserDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author snow
 */
@Service
public class SysUserDeptServiceImpl extends BaseServiceImpl implements SysUserDeptService {

    @Resource
    SnowflakeIdGenerator snowflakeIdGenerator;
    @Resource
    SysUserDeptMapper sysUserDeptMapper;

    @Override
    public int saveUserDept(SysUserDept sysUserDept) {
        sysUserDept.setId(snowflakeIdGenerator.snowflakeId());
        return sysUserDeptMapper.insert(sysUserDept);
    }
}
