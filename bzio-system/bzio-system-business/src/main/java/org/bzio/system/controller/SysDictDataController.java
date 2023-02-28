package org.bzio.system.controller;

import org.bzio.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.system.entity.SysDictData;
import org.bzio.system.service.SysDictDataService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统字典数据控制层
 *
 * @author snow
 */
@RestController
@RequestMapping("/dict/data")
public class SysDictDataController {

    @Resource
    SysDictDataService sysDictDataService;

    /**
     * 系统字典数据详情信息
     */
    @Log(title = "查询系统字典数据详情信息", businessType = BusinessType.QUERY)
    @GetMapping("info")
    public AjaxResult info(String configId) {
        return AjaxResult.success(sysDictDataService.queryInfo(configId));
    }

    /**
     * 系统字典数据列表
     */
    @Log(title = "查询系统字典数据列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    public AjaxResult list(SysDictData sysDictData) {
        return AjaxResult.success(sysDictDataService.queryAll(sysDictData));
    }

    /**
     * 保存系统字典数据
     */
    @Log(title = "新增或修改系统字典数据", businessType = BusinessType.INSERT)
    @PostMapping("saveDictData")
    public AjaxResult saveDictData(SysDictData sysDictData) {
        return AjaxResult.toAjax(sysDictDataService.saveDictData(sysDictData));
    }

    /**
     * 删除系统字典数据
     */
    @Log(title = "删除系统字典数据", businessType = BusinessType.DELETE)
    @PostMapping("delDictData")
    public AjaxResult delDictData(String configId) {
        return AjaxResult.toAjax(sysDictDataService.deleteDictData(configId));
    }
}
