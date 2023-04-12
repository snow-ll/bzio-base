package org.bzio.system.service;

import org.bzio.common.security.entity.MenuTreeNode;
import org.bzio.common.security.entity.SysMenu;

import java.util.List;

/**
 * @author: snow
 */
public interface SysMenuService {

    SysMenu queryInfo(String menuId);

    List<SysMenu> queryAll(SysMenu sysMenu);

    List<MenuTreeNode> queryTreeNode();

    List<MenuTreeNode> treeList(List<MenuTreeNode> treeNodes);

    int saveMenu(SysMenu sysMenu);

    int deleteMenu(String menu);
}
