package org.bzio.system.service.impl;

import org.bzio.common.core.util.IdUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.SysUserRole;
import org.bzio.common.security.mapper.SysUserRoleMapper;
import org.bzio.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: snow
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl implements SysUserRoleService {

    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public int saveUserRole(List<SysUserRole> sysUserRoles) {

        // 删除人员所有权限
        sysUserRoleMapper.deleteByUserId(sysUserRoles.get(0).getUserId());

        sysUserRoles.stream()
                .peek(sysUserRole ->
                    sysUserRole.setId(IdUtil.simpleUUID())
                ).collect(Collectors.toList());

        return sysUserRoleMapper.insertBatch(sysUserRoles);
    }
}
