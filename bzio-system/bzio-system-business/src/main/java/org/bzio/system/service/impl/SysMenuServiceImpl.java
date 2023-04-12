package org.bzio.system.service.impl;

import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.*;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.MenuTreeNode;
import org.bzio.common.security.entity.SysMenu;
import org.bzio.common.security.mapper.SysMenuMapper;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: snow
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl implements SysMenuService {

    @Resource
    SysMenuMapper sysMenuMapper;

    /**
     * 查询菜单详细信息
     */
    @Override
    public SysMenu queryInfo(String menuId) {
        return sysMenuMapper.queryById(menuId);
    }

    /**
     * 查询菜单集合
     */
    @Override
    public List<SysMenu> queryAll(SysMenu sysMenu) {
        return sysMenuMapper.queryAll(sysMenu);
    }

    /**
     * 查询树节点信息
     */
    @Override
    public List<MenuTreeNode> queryTreeNode() {
        return sysMenuMapper.queryTreeNode();
    }

    /**
     * 菜单树结构
     */
    @Override
    public List<MenuTreeNode> treeList(List<MenuTreeNode> treeNodes) {
        return TreeNodeUtil.buildTreeList(treeNodes);
    }

    /**
     * 保存菜单
     */
    @Override
    public int saveMenu(SysMenu sysMenu) {
        // 获取登录人信息
        String username = AuthUtil.getUsername();
        String nickname = AuthUtil.getNickname();

        // 判断传入的id是否为空
        if (StringUtil.isEmpty(sysMenu.getMenuId())) {
            sysMenu.setMenuId(IdUtil.simpleUUID());
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

    /**
     * 删除菜单
     */
    @Override
    public int deleteMenu(String menuId) {
        return sysMenuMapper.deleteById(menuId);
    }
}
