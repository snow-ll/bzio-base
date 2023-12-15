package org.bzio.system.service;

import org.bzio.common.security.entity.MenuTreeNode;
import org.bzio.common.security.entity.SysMenu;
import org.bzio.common.security.qo.SysMenuQo;

import java.util.List;
import java.util.Map;

/**
 * @author snow
 */
public interface SysMenuService {

    /**
     * 查询菜单详细信息
     */
    SysMenu queryInfo(String menuId);

    /**
     * 查询菜单集合
     */
    List<SysMenu> queryAll(SysMenu sysMenu);

    /**
     * 查询树节点信息
     */
    List<MenuTreeNode> queryTreeNode(SysMenuQo sysMenu);

    /**
     * 菜单树结构
     */
    List<MenuTreeNode> treeList(List<MenuTreeNode> treeNodes);

    /**
     * 查询子级菜单
     */
    List<Map> queryChild(String parentId);

    /**
     * 查询父级菜单
     */
    List<Map> queryParent(String menuId);

    /**
     * 保存菜单
     */
    int saveMenu(SysMenu sysMenu);

    /**
     * 批量删除菜单
     */
    int delBatch(String[] menuIds);
}
