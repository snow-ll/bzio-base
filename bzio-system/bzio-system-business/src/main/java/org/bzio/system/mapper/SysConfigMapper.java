package org.bzio.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bzio.system.entity.SysConfig;

import java.util.List;

/**
 * @author snow
 */
@Mapper
public interface SysConfigMapper {

    SysConfig queryById(String configId);

    List<SysConfig> queryAll(SysConfig sysConfig);

    int insert(SysConfig sysConfig);

    int update(SysConfig sysConfig);

    int deleteById(String configId);
}
