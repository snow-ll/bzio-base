package org.bzio.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bzio.common.core.web.entity.TreeNode;
import org.bzio.common.security.entity.SysMenu;

import java.util.List;

/**
 * @author: snow
 */
@Mapper
public interface SysMenuMapper {

    SysMenu queryById(String menuId);

    List<SysMenu> queryAll(SysMenu sysMenu);

    List<String> queryPermByUserId(String userId);

    List<TreeNode> queryTreeNode();

    int insert(SysMenu sysMenu);

    int update(SysMenu sysMenu);

    int deleteById(String menuId);
}
