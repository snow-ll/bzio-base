package org.bzio.common.core.util;

import java.util.UUID;

/**
 * 主键工具类
 *
 * @author: snow
 */
public class IDUtil {

    /**
     * 生成UUID
     *
     * @return
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线
     *
     * @return
     */
    public static String simpleUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
