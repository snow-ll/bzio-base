package org.bzio.system.controller;

import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.qo.SysUserQo;
import org.bzio.common.security.vo.SysUserVo;
import org.bzio.system.service.SysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统用户模块控制层
 *
 * @author snow
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
    @PreAuthorize("hasAnyAuthority('sys:user:search')")
    public AjaxResult info(String userId) {
        return AjaxResult.success(sysUserService.queryInfo(userId));
    }

    /**
     * 用户列表查询
     */
    @Log(title = "查询用户列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('sys:user:search')")
    public TableData list(SysUserQo sysUser) {
        startPage();
        List<SysUserVo> users = sysUserService.queryAll(sysUser);
        return getTableData(users);
    }

    /**
     * 保存用户
     */
    @Log(title = "新增或修改用户", businessType = BusinessType.INSERT)
    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('sys:user:add', 'sys:user:edit')")
    public AjaxResult save(@RequestBody SysUser sysUser) {
        return AjaxResult.toAjax(sysUserService.saveUser(sysUser));
    }

    /**
     * 删除用户
     */
    @Log(title = "删除用户", businessType = BusinessType.DELETE)
    @PostMapping("del")
    @PreAuthorize("hasAnyAuthority('sys:user:delete')")
    public AjaxResult del(@RequestBody String username) {
        return AjaxResult.toAjax(sysUserService.deleteUser(username));
    }

    /**
     * 修改用户状态
     */
    @Log(title = "修改用户状态", businessType = BusinessType.DELETE)
    @PostMapping("changeStatus")
    @PreAuthorize("hasAnyAuthority('sys:user:edit')")
    public AjaxResult changeStatus(@RequestBody SysUser sysUser) {
        return AjaxResult.toAjax(sysUserService.changeStatus(sysUser.getUserId(), sysUser.getStatus()));
    }
}
