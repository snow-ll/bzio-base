package org.bzio.system.controller;

import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.security.entity.SysUserRole;
import org.bzio.system.service.SysUserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户权限管理控制层
 *
 * @author: snow
 */
@RestController
@RequestMapping("/userRole")
public class SysUserRoleController {

    @Resource
    SysUserRoleService sysUserRoleService;

    /**
     * 用户添加权限
     */
    @Log(title = "用户添加权限", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public AjaxResult save(@RequestBody List<SysUserRole> sysUserRoles) {
        return AjaxResult.toAjax(sysUserRoleService.saveUserRole(sysUserRoles));
    }
}
