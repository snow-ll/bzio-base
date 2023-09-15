package org.bzio.system.controller;

import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.common.security.entity.SysMenu;
import org.bzio.common.security.qo.SysMenuQo;
import org.bzio.system.service.SysMenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单模块控制层
 *
 * @author snow
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
    @PreAuthorize("hasAnyAuthority('sys:menu:search')")
    public AjaxResult info(String menuId) {
        return AjaxResult.success(sysMenuService.queryInfo(menuId));
    }

    /**
     * 菜单列表查询
     */
    @Log(title = "查询菜单列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('sys:menu:search')")
    public TableData list(SysMenu sysMenu) {
        startPage();
        List<SysMenu> menus = sysMenuService.queryAll(sysMenu);
        return getTableData(menus);
    }

    /**
     * 菜单树状下拉列表
     */
    @GetMapping("tree")
    @PreAuthorize("hasAnyAuthority('sys:menu:search', 'sys:role:search')")
    public AjaxResult tree(SysMenuQo sysMenu) {
        return AjaxResult.success(sysMenuService.treeList(sysMenuService.queryTreeNode(sysMenu)));
    }

    /**
     * 查询子级菜单
     */
    @GetMapping("child")
    @PreAuthorize("hasAnyAuthority('sys:menu:search')")
    public AjaxResult child(String parentId) {
        return AjaxResult.success(sysMenuService.queryChild(parentId));
    }
    
    /**
     * 查询父级菜单
     */
    @GetMapping("parent")
    @PreAuthorize("hasAnyAuthority('sys:menu:search')")
    public AjaxResult parent(String menuId) {
        return AjaxResult.success(sysMenuService.queryParent(menuId));
    }

    /**
     * 保存菜单
     */
    @Log(title = "新增或修改菜单", businessType = BusinessType.INSERT)
    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('sys:menu:add', 'sys:menu:edit')")
    public AjaxResult save(@RequestBody SysMenu sysMenu) {
        return AjaxResult.toAjax(sysMenuService.saveMenu(sysMenu));
    }

    /**
     * 批量删除菜单
     */
    @Log(title = "批量删除菜单", businessType = BusinessType.DELETE)
    @PostMapping("delBatch")
    @PreAuthorize("hasAnyAuthority('sys:menu:delete')")
    public AjaxResult delBatch(@RequestBody String[] menuIds) {
        return AjaxResult.toAjax(sysMenuService.delBatch(menuIds));
    }
}
