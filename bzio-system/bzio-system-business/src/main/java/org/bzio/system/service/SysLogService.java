package org.bzio.system.service;

import org.bzio.system.entity.SysLog;

import java.util.List;

/**
 * @author snow
 */
public interface SysLogService {

    /**
     * 查询日志详情
     */
    SysLog queryInfo(String logId);

    /**
     * 查询日志列表
     */
    List<SysLog> queryAll(SysLog sySLog);

    /**
     * 保存日志
     */
    int saveLog(SysLog sysLog);
}
