package org.bzio.common.core.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 对象工具类
 *
 * @author: snow
 */
public class BeanUtil extends BeanUtils {

    /**
     * 复制非空属性到目标对象中
     *
     * @param src    源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 获取空属性字段名
     *
     * @return 值为null的属性名
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        // 获取属性
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        // 记录空值属性名集合
        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            // 根据属性名获取属性值
            Object srcValue = src.getPropertyValue(pd.getName());
            // 值为null记录集合中
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        // 转为数组
        return emptyNames.toArray(result);
    }

    /**
     * 对象转map
     *
     * @param o 需要转换的对象
     */
    public static Map beanToMap(Object o) {
        Map<String, Object> dataMap = new HashMap<>();
        Class<?> clazz = o.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            try {
                field.setAccessible(true);
                dataMap.put(field.getName(), field.get(o));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return dataMap;
    }
}
