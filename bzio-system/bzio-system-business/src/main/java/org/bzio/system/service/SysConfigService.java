package org.bzio.system.service;

import org.bzio.system.entity.SysConfig;

import java.util.List;

/**
 * @author snow
 */
public interface SysConfigService {

    SysConfig queryInfo(String configId);

    List<SysConfig> queryAll(SysConfig sysConfig);

    int saveConfig(SysConfig sysConfig);

    int deleteConfig(String configId);
}
