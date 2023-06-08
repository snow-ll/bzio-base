package org.bzio.system.service;

import org.bzio.common.security.entity.SysLog;

import java.util.List;

/**
 * @author snow
 */
public interface SysLogService {

    List<SysLog> queryAll(SysLog sySLog);

    SysLog queryInfo(String logId);
}
