package org.bzio.common.core.util.snowflake;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author snow
 */
@Component
@RefreshScope
public class SnowflakeConfig {

    @Value("${datacenterId}")
    private static long datacenterId;

    @Value("${machineId}")
    private static long machineId;

    public static long getDatacenterId() {
        return datacenterId;
    }

    public static long getMachineId() {
        return machineId;
    }
}
