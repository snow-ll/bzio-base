package org.bzio.common.security.entity;

import lombok.Data;
import org.bzio.common.core.web.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单权限表实体类
 *
 * @author snow
 */
@Data
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 5819360017642487499L;

    /**
     * 菜单主键
     */
    private String menuId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 父菜单ID
     */
    private String parentId;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件路径
     */
    private String component;
    /**
     * 是否为外链（0否 1是）
     */
    private Integer isFrame;
    /**
     * 是否缓存（0缓存 1不缓存）
     */
    private Integer isCache;
    /**
     * 菜单类型（M目录 C菜单 F按钮）
     */
    private String menuType;
    /**
     * 菜单状态（0显示 1隐藏）
     */
    private Integer visible;
    /**
     * 菜单状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 权限标识
     */
    private String perms;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 子菜单
     */
    private List<SysMenu> children = new ArrayList<>();
}

