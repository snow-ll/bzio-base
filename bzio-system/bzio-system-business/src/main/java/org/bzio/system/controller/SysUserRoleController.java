package org.bzio.system.controller;

import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.entity.SysUserRole;
import org.bzio.common.security.qo.SysUserQo;
import org.bzio.common.security.vo.SysUserRoleVo;
import org.bzio.system.service.SysUserRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户权限管理控制层
 *
 * @author snow
 */
@RestController
@RequestMapping("/userRole")
public class SysUserRoleController extends BaseController {

    @Resource
    SysUserRoleService sysUserRoleService;

    /**
     * 用户添加权限
     */
    @Log(title = "用户添加权限", businessType = BusinessType.EDIT)
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('sys:role:auth')")
    public AjaxResult save(@RequestBody List<SysUserRole> sysUserRoles) {
        return AjaxResult.toAjax(sysUserRoleService.saveUserRole(sysUserRoles));
    }


    /**
     * 用户授权
     */
    @Log(title = "用户授权", businessType = BusinessType.EDIT)
    @PostMapping("/authUser")
    @PreAuthorize("hasAnyAuthority('sys:role:auth')")
    public AjaxResult authUser(@RequestBody SysUserRoleVo sysUserRoleVo) {
        return AjaxResult.toAjax(sysUserRoleService.authUser(sysUserRoleVo));
    }
    

    /**
     * 根据角色关联关系查询用户
     */
    @Log(title = "根据角色关联关系查询用户", businessType = BusinessType.QUERY)
    @GetMapping("/queryUserByRole")
    @PreAuthorize("hasAnyAuthority('sys:role:search')")
    public TableData list(SysUserQo sysUserQo) {
        startPage();
        List<SysUser> users = sysUserRoleService.queryUserByRole(sysUserQo);
        return getTableData(users);
    }

    /**
     * 取消用户权限授权
     */
    @Log(title = "取消授权", businessType = BusinessType.QUERY)
    @PostMapping("/cancel")
    @PreAuthorize("hasAnyAuthority('sys:role:auth')")
    public AjaxResult cancel(@RequestBody SysUserRole sysUserRole) {
        return AjaxResult.toAjax(sysUserRoleService.cancel(sysUserRole));
    }
}
