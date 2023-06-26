package org.bzio.system.controller;

import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.common.log.annotation.Log;
import org.bzio.system.entity.SysDictType;
import org.bzio.system.service.SysDictTypeService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统字典类型控制层
 * 
 * @author snow
 */
@RestController
@RequestMapping("/dict/dict")
public class SysDictTypeController extends BaseController {

    @Resource
    SysDictTypeService sysDictTypeService;

    /**
     * 系统字典类型详情信息
     */
    @Log(title = "查询系统字典类型详情信息", businessType = BusinessType.QUERY)
    @GetMapping("info")
    @PreAuthorize("hasAnyAuthority('sys:dict:search')")
    public AjaxResult info(String dictId) {
        return AjaxResult.success(sysDictTypeService.queryInfo(dictId));
    }

    /**
     * 系统字典类型列表
     */
    @Log(title = "查询系统字典类型列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('sys:dict:search')")
    public TableData list(SysDictType sysDictType) {
        startPage();
        List<SysDictType> dataList = sysDictTypeService.queryAll(sysDictType);
        return getTableData(dataList);
    }

    /**
     * 保存系统字典类型
     */
    @Log(title = "新增或修改系统字典类型", businessType = BusinessType.INSERT)
    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('sys:dict:add', 'sys:dict:edit')")
    public AjaxResult save(@RequestBody SysDictType sysDictType) {
        return AjaxResult.toAjax(sysDictTypeService.saveDictType(sysDictType));
    }

    /**
     * 删除系统字典类型
     */
    @Log(title = "删除系统字典类型", businessType = BusinessType.DELETE)
    @PostMapping("del")
    @PreAuthorize("hasAnyAuthority('sys:dict:delete')")
    public AjaxResult del(@RequestBody String dictType) {
        return AjaxResult.toAjax(sysDictTypeService.deleteDictType(dictType));
    }
}
