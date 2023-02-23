package org.bzio.system.controller;

import org.bzio.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.system.entity.SysRole;
import org.bzio.system.service.SysRoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色模块控制层
 *
 * @author: snow
 */
@RestController
@RequestMapping("/role")
public class SysRoleController  extends BaseController {

    @Resource
    SysRoleService sysRoleService;

    /**
     * 角色详情信息
     */
    @Log(title = "查询角色详情", businessType = BusinessType.QUERY)
    @GetMapping("info")
    public AjaxResult info(String roleId) {
        return AjaxResult.success(sysRoleService.queryInfo(roleId));
    }

    /**
     * 角色列表查询
     */
    @Log(title = "查询角色列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    public TableData list(SysRole sysUser) {
        startPage();
        List<SysRole> users = sysRoleService.queryAll(sysUser);
        return getTableData(users);
    }

    /**
     * 保存角色
     */
    @Log(title = "新增或修改角色", businessType = BusinessType.INSERT)
    @PostMapping("saveRole")
    public AjaxResult saveRole(SysRole sysRole) {
        return AjaxResult.toAjax(sysRoleService.saveRole(sysRole));
    }

    /**
     * 删除角色
     */
    @Log(title = "删除角色", businessType = BusinessType.DELETE)
    @PostMapping("delRole")
    public AjaxResult delRole(String userName) {
        return AjaxResult.toAjax(sysRoleService.deleteRole(userName));
    }
}
