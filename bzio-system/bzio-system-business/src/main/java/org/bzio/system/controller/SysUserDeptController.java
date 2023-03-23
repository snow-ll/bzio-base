package org.bzio.system.controller;

import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.security.entity.SysUserDept;
import org.bzio.system.service.SysUserDeptService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统用户部门管理控制层
 *
 * @author: snow
 */
@RestController
@RequestMapping("/userDept")
public class SysUserDeptController {

    @Resource
    SysUserDeptService sysUserDeptService;

    /**
     * 用户添加到部门
     */
    @Log(title = "用户添加部门", businessType = BusinessType.INSERT)
    @PostMapping("/saveUserDept")
    public AjaxResult saveUserDept(SysUserDept sysUserDept) {
        return AjaxResult.toAjax(sysUserDeptService.saveUserDept(sysUserDept));
    }
}
