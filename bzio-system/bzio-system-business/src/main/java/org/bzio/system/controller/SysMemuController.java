package org.bzio.system.controller;

import org.bzio.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.system.entity.SysMenu;
import org.bzio.system.service.SysMenuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单模块控制层
 *
 * @author: snow
 */
@RestController
@RequestMapping("/menu")
public class SysMemuController extends BaseController {

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
    public AjaxResult tree() {
        return AjaxResult.success(sysMenuService.treeList(sysMenuService.queryTreeNode()));
    }

    /**
     * 保存菜单
     */
    @Log(title = "新增或修改菜单", businessType = BusinessType.INSERT)
    @PostMapping("saveMenu")
    public AjaxResult saveMenu(SysMenu sysMenu) {
        return AjaxResult.toAjax(sysMenuService.saveMenu(sysMenu));
    }

    /**
     * 删除菜单
     */
    @Log(title = "删除菜单", businessType = BusinessType.DELETE)
    @PostMapping("delMenu")
    public AjaxResult delMenu(String menuId) {
        return AjaxResult.toAjax(sysMenuService.deleteMenu(menuId));
    }
}
