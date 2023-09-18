package org.bzio.system.remote;


import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.system.entity.SysLog;
import org.bzio.system.factory.LogFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author snow
 */
@FeignClient(contextId = "remoteLogService", value = "bzio-system", fallback = LogFallbackFactory.class)
public interface RemoteLogService {

    @PostMapping("/log/save")
    AjaxResult<SysLog> saveLog(@RequestBody SysLog sysLog);
}
