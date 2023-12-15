package org.bzio.system.service;

import org.bzio.system.entity.SysConfig;

import java.util.List;

/**
 * @author snow
 */
public interface SysConfigService {

    /**
     * 查询配置详细信息
     */
    SysConfig queryInfo(String configId);

    /**
     * 查询配置列表
     */
    List<SysConfig> queryAll(SysConfig sysConfig);

    /**
     * 保存配置信息
     */
    int saveConfig(SysConfig sysConfig);

    /**
     * 删除配置信息
     */
    int deleteConfig(String configId);
}
