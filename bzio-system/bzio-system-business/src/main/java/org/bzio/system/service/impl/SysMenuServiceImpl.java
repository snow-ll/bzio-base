package org.bzio.system.service.impl;

import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.*;
import org.bzio.common.core.util.snowflake.SnowflakeIdGenerator;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.MenuTreeNode;
import org.bzio.common.security.entity.SysMenu;
import org.bzio.common.security.mapper.SysMenuMapper;
import org.bzio.common.security.qo.SysMenuQo;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.service.SysMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author snow
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl implements SysMenuService {

    @Resource
    SnowflakeIdGenerator snowflakeIdGenerator;
    @Resource
    SysMenuMapper sysMenuMapper;

    @Override
    public SysMenu queryInfo(String menuId) {
        return sysMenuMapper.queryById(menuId);
    }

    @Override
    public List<SysMenu> queryAll(SysMenu sysMenu) {
        return sysMenuMapper.queryAll(sysMenu);
    }

    @Override
    public List<MenuTreeNode> queryTreeNode(SysMenuQo sysMenu) {
        return sysMenuMapper.queryTreeNode(sysMenu);
    }

    @Override
    public List<MenuTreeNode> treeList(List<MenuTreeNode> treeNodes) {
        return TreeNodeUtil.buildTreeList(treeNodes);
    }

    @Override
    public List<Map> queryChild(String parentId) {
        return sysMenuMapper.queryChild(parentId);
    }

    @Override
    public List<Map> queryParent(String menuId) {
        return sysMenuMapper.queryParent(menuId);
    }


    @Override
    public int saveMenu(SysMenu sysMenu) {
        // 获取登录人信息
        String username = AuthUtil.getUsername();
        String nickname = AuthUtil.getNickname();

        // 判断传入的id是否为空
        if (StringUtil.isEmpty(sysMenu.getMenuId())) {
            sysMenu.setMenuId(snowflakeIdGenerator.snowflakeId());
            sysMenu.setCreateBy(username);
            sysMenu.setCreateName(nickname);
            sysMenu.setCreateDate(DateUtil.getNowDate());
            sysMenu.setUpdateBy(username);
            sysMenu.setUpdateName(nickname);
            sysMenu.setUpdateDate(DateUtil.getNowDate());
            return sysMenuMapper.insert(sysMenu);
        }else {
            SysMenu newMenu = sysMenuMapper.queryById(sysMenu.getMenuId());
            if (newMenu == null) throw new UserException("未查询到菜单信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysMenu, newMenu);
            newMenu.setUpdateBy(username);
            newMenu.setUpdateName(nickname);
            newMenu.setUpdateDate(DateUtil.getNowDate());
            return sysMenuMapper.update(newMenu);
        }
    }


    @Override
    @Transactional
    public int delBatch(String[] menuIds) {
        for (String menuId: menuIds) {
            deleteChild(menuId);
        }
        return sysMenuMapper.deleteBatch(menuIds);
    }

    /**
     * 递归删除子级 
     * @param parentId 当前父级id
     * @return
     */
    @Transactional
    public void deleteChild(String parentId) {
        List<Map> menus = sysMenuMapper.queryChild(parentId);

        for (Map menu: menus) {
            long isLeaf = (long) menu.get("isLeaf");
            String menuId = (String) menu.get("menuId");
            if (isLeaf != 1) {
                deleteChild(menuId);
            }
            sysMenuMapper.deleteById(menuId);
        }
    } 
}
