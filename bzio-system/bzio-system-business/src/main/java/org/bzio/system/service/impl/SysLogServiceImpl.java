package org.bzio.system.service.impl;

import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.BeanUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.entity.SysUserDept;
import org.bzio.common.security.util.AuthUtil;
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
    public SysLog queryInfo(String logId) {
        return sysLogMapper.queryById(logId);
    }

    @Override
    public List<SysLog> queryAll(SysLog sySLog) {
        return sysLogMapper.queryAll(sySLog);
    }

    @Override
    public int saveLog(SysLog sysLog) {
        return sysLogMapper.insert(sysLog);
    }
}
