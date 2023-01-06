package org.bzio.system.controller;

import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.system.entity.SysUserRole;
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
    @PostMapping("/saveUserRole")
    public AjaxResult saveUserRole(@RequestBody List<SysUserRole> sysUserRoles) {
        return AjaxResult.toAjax(sysUserRoleService.saveUserRole(sysUserRoles));
    }
}
