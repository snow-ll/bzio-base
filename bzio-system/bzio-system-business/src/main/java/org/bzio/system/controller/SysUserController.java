package org.bzio.system.controller;

import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.system.entity.SysUser;
import org.bzio.system.qo.SysUserQo;
import org.bzio.system.service.SysUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户模块控制层
 *
 * @author: snow
 */
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {

    @Resource
    SysUserService sysUserService;

    /**
     * 用户详情信息
     */
    @Log(title = "查询用户详情", businessType = BusinessType.QUERY)
    @GetMapping("info")
    public AjaxResult info(String username) {
        return AjaxResult.success(sysUserService.queryInfo(username));
    }

    /**
     * 用户列表查询
     */
    @Log(title = "查询用户列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    public TableData list(SysUserQo sysUser) {
        startPage();
        List<SysUser> users = sysUserService.queryAll(sysUser);
        return getTableData(users);
    }

    /**
     * 保存用户
     */
    @Log(title = "新增或修改用户", businessType = BusinessType.INSERT)
    @PostMapping("saveUser")
    public AjaxResult saveUser(SysUser sysUser) {
        return AjaxResult.toAjax(sysUserService.saveUser(sysUser));
    }

    /**
     * 删除用户
     */
    @Log(title = "删除用户", businessType = BusinessType.DELETE)
    @PostMapping("delUser")
    public AjaxResult delUser(String username) {
        return AjaxResult.toAjax(sysUserService.deleteUser(username));
    }
}
