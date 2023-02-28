package org.bzio.system.service;

import org.bzio.system.entity.SysLog;

import java.util.List;

/**
 * @author snow
 */
public interface SysLogService {

    List<SysLog> queryAll(SysLog sySLog);

    SysLog queryLogInfo(String logId);
}