package org.bzio.system.controller;

import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.system.entity.SysConfig;
import org.bzio.system.service.SysConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 系统参数控制层
 *
 * @author snow
 */
@RestController
@RequestMapping("/config")
public class SysConfigController {

    @Resource
    SysConfigService sysConfigService;

    /**
     * 系统参数详情信息
     */
    @Log(title = "查询系统参数详情信息", businessType = BusinessType.QUERY)
    @GetMapping("info")
    public AjaxResult info(String configId) {
        return AjaxResult.success(sysConfigService.queryInfo(configId));
    }

    /**
     * 系统参数列表
     */
    @Log(title = "查询系统参数列表", businessType = BusinessType.QUERY)
    @GetMapping("list")
    public AjaxResult list(SysConfig sysConfig) {
        return AjaxResult.success(sysConfigService.queryAll(sysConfig));
    }

    /**
     * 保存系统参数
     */
    @Log(title = "新增或修改系统参数", businessType = BusinessType.INSERT)
    @PostMapping("saveConfig")
    public AjaxResult saveConfig(SysConfig sysConfig) {
        return AjaxResult.toAjax(sysConfigService.saveConfig(sysConfig));
    }

    /**
     * 删除系统参数
     */
    @Log(title = "删除系统参数", businessType = BusinessType.DELETE)
    @PostMapping("delConfig")
    public AjaxResult delConfig(String configId) {
        return AjaxResult.toAjax(sysConfigService.deleteConfig(configId));
    }
}
