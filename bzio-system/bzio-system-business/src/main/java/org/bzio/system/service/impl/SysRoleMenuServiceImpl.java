package org.bzio.system.service.impl;

import org.bzio.common.core.util.snowflake.SnowflakeIdGenerator;
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
    SnowflakeIdGenerator snowflakeIdGenerator;
    @Resource
    SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public List<String> queryMenuIdsByRoleId(String roleId) {
        return sysRoleMenuMapper.queryMenuIdsByRoleId(roleId);
    }


    @Override
    public int insertBatch(String roleId, List<String> menuIds) {
        int result = 0;
        SysRoleMenu sysRoleMenu = new SysRoleMenu();
        // 添加菜单权限
        for (String menuId : menuIds) {
            sysRoleMenu.setId(snowflakeIdGenerator.snowflakeId());
            sysRoleMenu.setRoleId(roleId);
            sysRoleMenu.setMenuId(menuId);
            result += sysRoleMenuMapper.insert(sysRoleMenu);
        }
        return result;
    }

    @Override
    public int clearMenu(String roleId) {
        return sysRoleMenuMapper.deleteByRoleId(roleId);
    }
}
