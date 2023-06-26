package org.bzio.system.controller;

import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.log.annotation.Log;
import org.bzio.system.service.SysRoleMenuService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统角色菜单管理控制层
 * 
 * @author snow
 */
@RestController
@RequestMapping("/roleMenu")
public class SysRoleMenuController extends BaseController {
    
    @Resource
    SysRoleMenuService sysRoleMenuService;

    /**
     * 查询当前角色拥有访问权限的菜单
     */
    @Log(title = "查询当前角色拥有访问权限的菜单", businessType = BusinessType.QUERY)
    @GetMapping("queryMenuIds")
    @PreAuthorize("hasAnyAuthority('sys:role:search')")
    public AjaxResult queryMenuIds(String roleId) {
        return AjaxResult.success(sysRoleMenuService.queryMenuIdsByRoleId(roleId));
    }
}
