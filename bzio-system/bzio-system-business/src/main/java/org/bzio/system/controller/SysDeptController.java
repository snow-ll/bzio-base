package org.bzio.system.controller;

import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.common.security.entity.SysDept;
import org.bzio.system.service.SysDeptService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 部门模块控制层
 *
 * @author snow
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController extends BaseController {

    @Resource
    SysDeptService sysDeptService;

    /**
     * 部门详情信息
     */
    @Log(title = "查询部门详情", businessType = BusinessType.QUERY)
    @GetMapping("info")
    @PreAuthorize("hasAnyAuthority('sys:dept:search')")
    public AjaxResult info(String deptId) {
        return AjaxResult.success(sysDeptService.queryInfo(deptId));
    }

    /**
     * 部门列表查询
     */
    @Log(title = "查询部门列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('sys:dept:search')")
    public TableData list(SysDept sysDept) {
        startPage();
        List<SysDept> depts = sysDeptService.queryAll(sysDept);
        return getTableData(depts);
    }

    /**
     * 部门树状下拉列表
     */
    @GetMapping("tree")
    @PreAuthorize("hasAnyAuthority('sys:dept:search')")
    public AjaxResult tree() {
        return AjaxResult.success(sysDeptService.treeList(sysDeptService.queryTreeNode()));
    }

    /**
     * 保存部门
     */
    @Log(title = "新增或修改部门", businessType = BusinessType.INSERT)
    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('sys:dept:add', 'sys:dept:edit')")
    public AjaxResult save(@RequestBody SysDept sysDept) {
        return AjaxResult.toAjax(sysDeptService.saveDept(sysDept));
    }

    /**
     * 批量删除部门
     */
    @Log(title = "批量删除部门", businessType = BusinessType.DELETE)
    @PostMapping("delBatch")
    @PreAuthorize("hasAnyAuthority('sys:dept:delete')")
    public AjaxResult delBatch(@RequestBody String[] deptIds) {
        return AjaxResult.toAjax(sysDeptService.delBatch(deptIds));
    }
}
