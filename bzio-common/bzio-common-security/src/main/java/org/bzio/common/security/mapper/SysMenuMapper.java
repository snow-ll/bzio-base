package org.bzio.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bzio.common.security.entity.MenuTreeNode;
import org.bzio.common.security.entity.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * @author: snow
 */
@Mapper
public interface SysMenuMapper {

    SysMenu queryById(String menuId);

    List<SysMenu> queryAll(SysMenu sysMenu);

    List<String> queryPermByUserId(String userId);

    List<MenuTreeNode> queryTreeNode(SysMenu sysMenu);
    
    List<Map> queryChild(@Param("parentId") String parentId);
    
    List<Map> queryParent(@Param("menuId") String menuId);

    int insert(SysMenu sysMenu);

    int update(SysMenu sysMenu);

    int deleteById(String menuId);
    
    int deleteBatch(@Param("menuIds") String[] menuIds);
}
