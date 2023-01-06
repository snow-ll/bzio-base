package org.bzio.system.controller;

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
    @GetMapping("info")
    public AjaxResult info(String roleId) {
        return AjaxResult.success(sysRoleService.queryInfo(roleId));
    }

    /**
     * 角色列表查询
     */
    @GetMapping("list")
    public TableData list(SysRole sysUser) {
        startPage();
        List<SysRole> users = sysRoleService.queryAll(sysUser);
        return getTableData(users);
    }

    /**
     * 保存角色
     */
    @PostMapping("saveRole")
    public AjaxResult saveRole(SysRole sysRole) {
        return AjaxResult.toAjax(sysRoleService.saveRole(sysRole));
    }

    /**
     * 删除角色
     */
    @PostMapping("delRole")
    public AjaxResult delRole(String userName) {
        return AjaxResult.toAjax(sysRoleService.deleteRole(userName));
    }
}
