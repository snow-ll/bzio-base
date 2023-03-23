package org.bzio.system.service;

import org.bzio.common.core.web.entity.TreeNode;
import org.bzio.common.security.entity.SysMenu;

import java.util.List;

/**
 * @author: snow
 */
public interface SysMenuService {

    SysMenu queryInfo(String menuId);

    List<SysMenu> queryAll(SysMenu sysMenu);

    List<TreeNode> queryTreeNode();

    List<TreeNode> treeList(List<TreeNode> treeSelectList);

    int saveMenu(SysMenu sysMenu);

    int deleteMenu(String menu);
}
