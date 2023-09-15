package org.bzio.system.controller;

import org.bzio.common.core.util.StringUtil;
import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.common.security.entity.SysRole;
import org.bzio.common.security.entity.SysRoleMenu;
import org.bzio.common.security.mapper.SysRoleMenuMapper;
import org.bzio.system.service.SysRoleMenuService;
import org.bzio.system.service.SysRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色模块控制层
 *
 * @author snow
 */
@RestController
@RequestMapping("/role")
public class SysRoleController  extends BaseController {

    @Resource
    SysRoleService sysRoleService;
    
    @Resource
    SysRoleMenuService sysRoleMenuService;

    /**
     * 角色详情信息
     */
    @Log(title = "查询角色详情", businessType = BusinessType.QUERY)
    @GetMapping("info")
    @PreAuthorize("hasAnyAuthority('sys:role:search')")
    public AjaxResult info(String roleId) {
        return AjaxResult.success(sysRoleService.queryInfo(roleId));
    }

    /**
     * 角色列表查询
     */
    @Log(title = "查询角色列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('sys:role:search')")
    public TableData list(SysRole sysUser) {
        startPage();
        List<SysRole> users = sysRoleService.queryAll(sysUser);
        return getTableData(users);
    }

    /**
     * 保存角色
     */
    @Log(title = "新增或修改角色", businessType = BusinessType.INSERT)
    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('sys:role:add', 'sys:role:edit')")
    public AjaxResult save(@RequestBody SysRole sysRole) {
        // 保存角色信息
        int result = sysRoleService.saveRole(sysRole);
        if (result == 1) {
            // 重新添加角色有权限的菜单前清空原有的菜单
            sysRoleMenuService.clearMenu(sysRole.getRoleId());
            // 保存角色关联的菜单
            if (StringUtil.isNotNull(sysRole.getMenuIds())) {
                result = sysRoleMenuService.insertBatch(sysRole.getRoleId(), sysRole.getMenuIds());
            }
            return AjaxResult.toAjax(result, false, "保存角色信息成功！", "保存当前角色关联菜单异常！");
        }
        return AjaxResult.success( "保存角色信息成功！");
    }

    /**
     * 删除角色
     */
    @Log(title = "删除角色", businessType = BusinessType.DELETE)
    @PostMapping("del")
    @PreAuthorize("hasAnyAuthority('sys:role:delete')")
    public AjaxResult del(@RequestBody String roleId) {
        return AjaxResult.toAjax(sysRoleService.deleteRole(roleId));
    }
}
