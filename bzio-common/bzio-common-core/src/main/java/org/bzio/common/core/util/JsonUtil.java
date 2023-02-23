package org.bzio.common.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Json工具类
 *
 * @author snow
 */
public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);

    /**
     * 加载外部类时不加在静态内部类，实现懒加载
     */
    private static class ObjectMapperHolder {
        private final static ObjectMapper objectMapper = new ObjectMapper();
    }

    private static ObjectMapper getInstance() {
        return ObjectMapperHolder.objectMapper;
    }

    /**
     * 简单json转换
     */
    public static <T> T jsonToObject(String jsonStr, Class c) {
        ObjectMapper objectMapper = getInstance();
        try {
            return (T) objectMapper.readValue(jsonStr, c);
        } catch (JsonProcessingException e) {
            log.error("解析异常：", e);
            return null;
        }
    }

    /**
     * 对象转换成json字符串
     */
    public static String toJSONString(Object o) {
        ObjectMapper objectMapper = getInstance();
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("解析异常：", e);
            return null;
        }
    }
}