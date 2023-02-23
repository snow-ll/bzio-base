package org.bzio.system.service.impl;

import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.BeanUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.IdUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.entity.SysRole;
import org.bzio.system.mapper.SysRoleMapper;
import org.bzio.system.service.SysRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: snow
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl implements SysRoleService {

    @Resource
    SysRoleMapper sysRoleMapper;

    /**
     * 角色详情信息
     */
    @Override
    public SysRole queryInfo(String roleId) {
        return sysRoleMapper.queryById(roleId);
    }

    /**
     * 角色列表查询
     */
    @Override
    public List<SysRole> queryAll(SysRole sysRole) {
        return sysRoleMapper.queryAll(sysRole);
    }

    /**
     * 保存角色
     */
    @Override
    public int saveRole(SysRole sysRole) {
        // 获取登录人信息
        String userName = AuthUtil.getUserName();
        String nickName = AuthUtil.getNickName();

        // 判断传入的id是否为空
        // 为空新增用户
        if (StringUtil.isEmpty(sysRole.getRoleId())) {
            sysRole.setRoleId(IdUtil.simpleUUID());
            sysRole.setCreateBy(userName);
            sysRole.setCreateName(nickName);
            sysRole.setCreateDate(DateUtil.getNowDate());
            sysRole.setUpdateBy(userName);
            sysRole.setUpdateName(nickName);
            sysRole.setUpdateDate(DateUtil.getNowDate());
            return sysRoleMapper.insert(sysRole);
        }else {
            SysRole newRole = sysRoleMapper.queryById(sysRole.getRoleId());
            if (newRole == null) throw new UserException("未查询到角色信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysRole, newRole);
            newRole.setUpdateBy(userName);
            newRole.setUpdateName(nickName);
            newRole.setUpdateDate(DateUtil.getNowDate());
            return sysRoleMapper.update(newRole);
        }
    }

    /**
     * 删除角色
     */
    @Override
    public int deleteRole(String roleId) {
        return sysRoleMapper.deleteById(roleId);
    }
}
