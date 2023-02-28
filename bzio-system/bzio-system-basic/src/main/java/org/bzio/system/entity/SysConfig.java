package org.bzio.system.entity;

import org.bzio.common.core.web.entity.BaseEntity;

import java.io.Serializable;

/**
 * 系统参数实体类
 *
 * @author snow
 */
public class SysConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 505170173396504308L;

    private String configId;

    private String configName;

    private String configKey;

    private String configValue;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigKey(String configKey) {
        this.configKey = configKey;
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }
}