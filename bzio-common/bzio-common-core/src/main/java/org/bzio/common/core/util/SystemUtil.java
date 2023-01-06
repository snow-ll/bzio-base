package org.bzio.common.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 系统环境工具类
 *
 * @author: snow
 */
public class SystemUtil {

    private static final Logger logger = LoggerFactory.getLogger(SystemUtil.class);

    /**
     * 获取ip信息
     *
     * @return ip信息
     */
    public static InetAddress getLocalHost() {
        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            logger.error("获取ip信息出现异常");
            return null;
        }
    }

    /**
     * 获取ip地址
     * @return ip地址
     */
    public static String getLocalIp() {
        InetAddress inetAddress = getLocalHost();
        if (inetAddress == null)
            return null;
        else
            return inetAddress.getHostAddress();
    }
}
