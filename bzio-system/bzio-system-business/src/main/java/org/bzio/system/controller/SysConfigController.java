package org.bzio.system.controller;

import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.TableData;
import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.system.entity.SysConfig;
import org.bzio.system.service.SysConfigService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统参数控制层
 *
 * @author snow
 */
@RestController
@RequestMapping("/config")
public class SysConfigController extends BaseController {

    @Resource
    SysConfigService sysConfigService;

    /**
     * 系统参数详情信息
     */
    @Log(title = "查询系统参数详情信息", businessType = BusinessType.QUERY)
    @GetMapping("info")
    @PreAuthorize("hasAnyAuthority('sys:config:search')")
    public AjaxResult info(String configId) {
        return AjaxResult.success(sysConfigService.queryInfo(configId));
    }

    /**
     * 系统参数列表
     */
    @Log(title = "查询系统参数列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    @PreAuthorize("hasAnyAuthority('sys:config:search')")
    public TableData list(SysConfig sysConfig) {
        startPage();
        List<SysConfig> configs = sysConfigService.queryAll(sysConfig);
        return getTableData(configs);
    }

    /**
     * 保存系统参数
     */
    @Log(title = "新增或修改系统参数", businessType = BusinessType.EDIT)
    @PostMapping("save")
    @PreAuthorize("hasAnyAuthority('sys:config:add', 'sys:config:edit')")
    public AjaxResult save(@RequestBody SysConfig sysConfig) {
        return AjaxResult.toAjax(sysConfigService.saveConfig(sysConfig));
    }

    /**
     * 删除系统参数
     */
    @Log(title = "删除系统参数", businessType = BusinessType.DELETE)
    @PostMapping("del")
    @PreAuthorize("hasAnyAuthority('sys:config:delete')")
    public AjaxResult del(@RequestBody String configId) {
        return AjaxResult.toAjax(sysConfigService.deleteConfig(configId));
    }
}
