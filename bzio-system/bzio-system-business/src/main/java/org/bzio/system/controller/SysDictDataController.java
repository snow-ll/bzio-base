package org.bzio.system.controller;

import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.system.entity.SysDictData;
import org.bzio.system.service.SysDictDataService;
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
    public AjaxResult info(String configId) {
        return AjaxResult.success(sysDictDataService.queryInfo(configId));
    }

    /**
     * 系统字典数据列表
     */
    @Log(title = "查询系统字典数据列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
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
        sysDictData.setStatus(0);
        return AjaxResult.success(sysDictDataService.queryDictData(sysDictData));
    }
    
    /**
     * 保存系统字典数据
     */
    @Log(title = "新增或修改系统字典数据", businessType = BusinessType.INSERT)
    @PostMapping("save")
    public AjaxResult save(SysDictData sysDictData) {
        return AjaxResult.toAjax(sysDictDataService.saveDictData(sysDictData));
    }

    /**
     * 删除系统字典数据
     */
    @Log(title = "删除系统字典数据", businessType = BusinessType.DELETE)
    @PostMapping("del")
    public AjaxResult del(String configId) {
        return AjaxResult.toAjax(sysDictDataService.deleteDictData(configId));
    }
}
