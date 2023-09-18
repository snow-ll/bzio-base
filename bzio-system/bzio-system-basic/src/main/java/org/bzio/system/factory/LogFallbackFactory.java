package org.bzio.system.factory;

import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.system.entity.SysLog;
import org.bzio.system.remote.RemoteLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;


/**
 * @author snow
 */
@Component
public class LogFallbackFactory implements FallbackFactory<RemoteLogService> {

    private static final Logger log = LoggerFactory.getLogger(LogFallbackFactory.class);

    @Override
    public RemoteLogService create(Throwable cause) {
        log.error("日志服务调用失败:{}", cause.getMessage());

        return new RemoteLogService() {
            @Override
            public AjaxResult<SysLog> saveLog(SysLog sysLog) {
                return AjaxResult.error("保存操作日志失败");
            }
        };
    }
}
