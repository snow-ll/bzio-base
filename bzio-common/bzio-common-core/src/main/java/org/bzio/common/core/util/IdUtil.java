package org.bzio.common.core.util;

import java.util.UUID;

/**
 * 主键工具类
 *
 * @author snow
 */
public class IdUtil {

    public static void main(String[] args) {
        System.out.println(simpleUUID());
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
}
