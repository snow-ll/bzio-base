package org.bzio.system.controller;

import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.system.entity.SysDept;
import org.bzio.system.service.SysDeptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 部门模块控制层
 *
 * @author: snow
 */
@RestController
@RequestMapping("/dept")
public class SysDeptController extends BaseController {

    @Resource
    SysDeptService sysDeptService;

    /**
     * 部门详情信息
     */
    @GetMapping("info")
    public AjaxResult info(String deptId) {
        return AjaxResult.success(sysDeptService.queryInfo(deptId));
    }

    /**
     * 部门列表查询
     */
    @GetMapping("list")
    public TableData list(SysDept sysDept) {
        startPage();
        List<SysDept> depts = sysDeptService.queryAll(sysDept);
        return getTableData(depts);
    }

    /**
     * 部门树状下拉列表
     */
    @GetMapping("tree")
    public AjaxResult tree() {
        List<SysDept> depts = sysDeptService.queryAll(new SysDept());
        return AjaxResult.success(sysDeptService.treeList(depts));
    }

    /**
     * 保存部门
     */
    @PostMapping("saveDept")
    public AjaxResult saveDept(SysDept sysDept) {
        return AjaxResult.toAjax(sysDeptService.saveDept(sysDept));
    }

    /**
     * 删除部门
     */
    @PostMapping("delDept")
    public AjaxResult delDept(String deptId) {
        return AjaxResult.toAjax(sysDeptService.deleteDept(deptId));
    }
}
