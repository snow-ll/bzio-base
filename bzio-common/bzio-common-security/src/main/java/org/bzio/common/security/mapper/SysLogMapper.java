package org.bzio.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bzio.common.security.entity.SysLog;

import java.util.List;

/**
 * @author snow
 */
@Mapper
public interface SysLogMapper {

    SysLog queryById(String logId);

    List<SysLog> queryAll(SysLog sySLog);

    int insert(SysLog sySLog);
}
