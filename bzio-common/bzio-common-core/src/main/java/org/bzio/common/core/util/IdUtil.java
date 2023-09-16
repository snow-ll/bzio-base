package org.bzio.common.core.util;

import org.bzio.common.core.util.snowflake.SnowflakeIdGenerator;

import java.util.UUID;

/**
 * 主键工具类
 *
 * @author snow
 */
public class IdUtil {

    /**
     * 雪花id
     */
    public static String snowflakeId() {
        return Long.toString(SnowflakeIdGenerator.getInstance(1,1).nextId());
    }

    /**
     * 生成UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     */
    public static String simpleUUID() {
        return randomUUID().replace("-", "");
    }

    public static void main(String[] args) {
        System.out.println(snowflakeId());
    }
}
