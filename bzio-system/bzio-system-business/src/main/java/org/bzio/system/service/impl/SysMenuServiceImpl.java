package org.bzio.system.service.impl;

import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.*;
import org.bzio.common.core.web.entity.TreeNode;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.entity.SysMenu;
import org.bzio.system.mapper.SysMenuMapper;
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
    public List<TreeNode> queryTreeNode() {
        return sysMenuMapper.queryTreeNode();
    }

    /**
     * 菜单树结构
     */
    @Override
    public List<TreeNode> treeList(List<TreeNode> treeNodeList) {
        return TreeNodeUtil.buildTreeList(treeNodeList);
    }

    /**
     * 保存菜单
     */
    @Override
    public int saveMenu(SysMenu sysMenu) {
        // 获取登录人信息
        String userName = AuthUtil.getUserName();
        String nickName = AuthUtil.getNickName();

        // 判断传入的id是否为空
        if (StringUtil.isEmpty(sysMenu.getMenuId())) {
            sysMenu.setMenuId(IdUtil.simpleUUID());
            sysMenu.setCreateBy(userName);
            sysMenu.setCreateName(nickName);
            sysMenu.setCreateDate(DateUtil.getNowDate());
            sysMenu.setUpdateBy(userName);
            sysMenu.setUpdateName(nickName);
            sysMenu.setUpdateDate(DateUtil.getNowDate());
            return sysMenuMapper.insert(sysMenu);
        }else {
            SysMenu newMenu = sysMenuMapper.queryById(sysMenu.getMenuId());
            if (newMenu == null) throw new UserException("未查询到菜单信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysMenu, newMenu);
            newMenu.setUpdateBy(userName);
            newMenu.setUpdateName(nickName);
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
