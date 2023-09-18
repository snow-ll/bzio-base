package org.bzio.system.controller;

import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.system.entity.SysLog;
import org.bzio.system.service.SysLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author snow
 */
@RestController
@RequestMapping("/log")
public class SysLogController {

    @Resource
    SysLogService sysLogService;

    @GetMapping("list")
    public AjaxResult list(@RequestBody SysLog sysLog) {
        return AjaxResult.success(sysLogService.queryAll(sysLog));
    }

    /**
     * 保存日志
     */
    @PostMapping("save")
    public AjaxResult save(@RequestBody SysLog sysLog) {
        return AjaxResult.toAjax(sysLogService.saveLog(sysLog));
    }
}
