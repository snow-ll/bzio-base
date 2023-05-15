package org.bzio.system.service;

import org.bzio.common.security.entity.MenuTreeNode;
import org.bzio.common.security.entity.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * @author: snow
 */
public interface SysMenuService {

    SysMenu queryInfo(String menuId);

    List<SysMenu> queryAll(SysMenu sysMenu);

    List<MenuTreeNode> queryTreeNode(SysMenu sysMenu);

    List<MenuTreeNode> treeList(List<MenuTreeNode> treeNodes);
    
    List<Map> queryChild(String parentId);
    
    List<Map> queryParent(String menuId);

    int saveMenu(SysMenu sysMenu);

    int deleteMenu(String[] menuIds);
}
