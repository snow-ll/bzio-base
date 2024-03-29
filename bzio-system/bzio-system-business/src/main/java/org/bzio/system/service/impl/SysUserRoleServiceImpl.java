package org.bzio.system.service.impl;

import org.bzio.common.core.util.snowflake.SnowflakeIdGenerator;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.entity.SysUserRole;
import org.bzio.common.security.mapper.SysUserRoleMapper;
import org.bzio.common.security.qo.SysUserQo;
import org.bzio.common.security.vo.SysUserRoleVo;
import org.bzio.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author snow
 */
@Service
public class SysUserRoleServiceImpl extends BaseServiceImpl implements SysUserRoleService {

    @Resource
    SnowflakeIdGenerator snowflakeIdGenerator;
    @Resource
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUser> queryUserByRole(SysUserQo sysUserQo) {
        return sysUserRoleMapper.queryUserByRole(sysUserQo);
    }

    @Override
    public int saveUserRole(List<SysUserRole> sysUserRoles) {

        // 删除人员所有权限
        sysUserRoleMapper.deleteByUserId(sysUserRoles.get(0).getUserId());

        sysUserRoles.stream()
                .peek(sysUserRole ->
                    sysUserRole.setId(snowflakeIdGenerator.snowflakeId())
                ).collect(Collectors.toList());

        return sysUserRoleMapper.insertBatch(sysUserRoles);
    }

    @Override
    public int authUser(SysUserRoleVo sysUserRoleVo) {
        int result = 0;
        List<String> userIds = sysUserRoleVo.getUserIds();
        SysUserRole sysUserRole = new SysUserRole();

        for (String userId: userIds) {
            sysUserRole.setId(snowflakeIdGenerator.snowflakeId());
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(sysUserRoleVo.getRoleId());
            result += sysUserRoleMapper.insert(sysUserRole);
        }
        return result;
    }

    @Override
    public int cancel(SysUserRole sysUserRole) {
        return sysUserRoleMapper.delete(sysUserRole);
    }
}
