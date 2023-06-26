package org.bzio.system.service.impl;

import org.bzio.common.core.util.IdUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.SysRoleMenu;
import org.bzio.common.security.mapper.SysRoleMenuMapper;
import org.bzio.system.service.SysRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author snow
 */
@Service
public class SysRoleMenuServiceImpl extends BaseServiceImpl implements SysRoleMenuService {

    @Resource
    SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 获取菜单id
     */
    @Override
    public List<String> queryMenuIdsByRoleId(String roleId) {
        return sysRoleMenuMapper.queryMenuIdsByRoleId(roleId);
    }

    /**
     * 批量给角色添加菜单访问权限
     * @param roleId 添加菜单角色
     * @param menuIds 添加菜单的id
     * @return
     */
    @Override
    public int insertBatch(String roleId, List<String> menuIds) {
        int result = 0;
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        // 添加菜单权限
        for (String menuId : menuIds) {
            sysRoleMenu.setId(IdUtil.simpleUUID());
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            result += sysRoleMenuMapper.insert(sysRoleMenu);
        }
        return result;
    }

    /**
     * 清空原有的菜单权限
     */
    @Override
    public int clearMenu(String roleId) {
        return sysRoleMenuMapper.deleteByRoleId(roleId);
    }
}
