package org.bzio.system.service.impl;

import org.bzio.system.entity.SysLog;
import org.bzio.system.mapper.SysLogMapper;
import org.bzio.system.service.SysLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author snow
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Resource
    private SysLogMapper sysLogMapper;

    @Override
    public List<SysLog> queryAll(SysLog sySLog) {
        return sysLogMapper.queryAll(sySLog);
    }

    @Override
    public SysLog queryLogInfo(String logId) {
        return sysLogMapper.queryById(logId);
    }
}
