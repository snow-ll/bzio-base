package org.bzio.common.core.util.snowflake;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author snow
 */
@Component
@RefreshScope
public class SnowflakeConfig {

    @Value("${snowflake.datacenterId}")
    private long datacenterId;

    @Value("${snowflake.machineId}")
    private long machineId;

    @Bean
    public SnowflakeIdGenerator getSnowFlakeFactory() {
        SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(datacenterId,machineId);
        return snowflakeIdGenerator;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }
}
