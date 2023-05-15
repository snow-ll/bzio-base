package org.bzio.system.controller;

import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.common.security.entity.SysMenu;
import org.bzio.system.service.SysMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单模块控制层
 *
 * @author: snow
 */
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {

    @Resource
    SysMenuService sysMenuService;

    /**
     * 菜单详情信息
     */
    @Log(title = "查询菜单详情", businessType = BusinessType.QUERY)
    @GetMapping("info")
    public AjaxResult info(String menuId) {
        return AjaxResult.success(sysMenuService.queryInfo(menuId));
    }

    /**
     * 菜单列表查询
     */
    @Log(title = "查询菜单列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    public TableData list(SysMenu sysMenu) {
        startPage();
        List<SysMenu> menus = sysMenuService.queryAll(sysMenu);
        return getTableData(menus);
    }

    /**
     * 菜单树状下拉列表
     */
    @GetMapping("tree")
    public AjaxResult tree(SysMenu sysMenu) {
        return AjaxResult.success(sysMenuService.treeList(sysMenuService.queryTreeNode(sysMenu)));
    }

    /**
     * 查询子级菜单
     */
    @GetMapping("child")
    public AjaxResult child(String parentId) {
        return AjaxResult.success(sysMenuService.queryChild(parentId));
    }
    
    /**
     * 查询父级菜单
     */
    @GetMapping("parent")
    public AjaxResult parent(String menuId) {
        return AjaxResult.success(sysMenuService.queryParent(menuId));
    }

    /**
     * 保存菜单
     */
    @Log(title = "新增或修改菜单", businessType = BusinessType.INSERT)
    @PostMapping("save")
    public AjaxResult save(@RequestBody SysMenu sysMenu) {
        return AjaxResult.toAjax(sysMenuService.saveMenu(sysMenu));
    }

    /**
     * 删除菜单
     */
    @Log(title = "删除菜单", businessType = BusinessType.DELETE)
    @PostMapping("del")
    public AjaxResult del(@RequestBody String[] menuIds) {
        return AjaxResult.toAjax(sysMenuService.deleteMenu(menuIds));
    }
}
