package org.bzio.system.controller;

import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.system.entity.SysDictData;
import org.bzio.system.service.SysDictDataService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统字典数据控制层
 *
 * @author snow
 */
@RestController
@RequestMapping("/dict/data")
public class SysDictDataController extends BaseController {
    
    @Resource
    SysDictDataService sysDictDataService;

    /**
     * 系统字典数据详情信息
     */
    @Log(title = "查询系统字典数据详情信息", businessType = BusinessType.QUERY)
    @GetMapping("info")
    @PreAuthorize("hasAnyAuthority('sys:dict:search')")
    public AjaxResult info(String dictCode) {
        return AjaxResult.success(sysDictDataService.queryInfo(dictCode));
    }

    /**
     * 系统字典数据列表
     */
    @Log(title = "查询系统字典数据列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('sys:dict:search')")
    public TableData list(SysDictData sysDictData) {
        startPage();
        List<SysDictData> dataList = sysDictDataService.queryAll(sysDictData);
        return getTableData(dataList);
    }

    /**
     * 根据条件查询字典数据
     */
    @Log(title = "根据条件查询字典数据", businessType = BusinessType.QUERY)
    @GetMapping("type")
    public AjaxResult type(SysDictData sysDictData) {
        return AjaxResult.success(sysDictDataService.queryByType(sysDictData));
    }
    
    /**
     * 保存系统字典数据
     */
    @Log(title = "新增或修改系统字典数据", businessType = BusinessType.INSERT)
    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('sys:dict:add', 'sys:dict:edit')")
    public AjaxResult save(@RequestBody SysDictData sysDictData) {
        return AjaxResult.toAjax(sysDictDataService.saveDictData(sysDictData));
    }

    /**
     * 删除系统字典数据
     */
    @Log(title = "删除系统字典数据", businessType = BusinessType.DELETE)
    @PostMapping("del")
    @PreAuthorize("hasAnyAuthority('sys:dict:delete')")
    public AjaxResult del(@RequestBody String dictCode) {
        return AjaxResult.toAjax(sysDictDataService.deleteDictData(dictCode));
    }
}
