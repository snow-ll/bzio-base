package org.bzio.common.core.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * 字符串相关工具类
 *
 * @author: snow
 */
public class StringUtil {

    // 空字符串
    private static final String EMPTY_STR = "";

    /**
     * 判断字符串是否为空
     *
     * @return true：为空 false：非空
     */
    public static boolean isEmpty(String str) {
        return isNull(str)|| EMPTY_STR.equals(str.trim());
    }

    /**
     * 判断字符串是否为空
     *
     * @return true：非空 false：为空
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：为空 false：非空
     */
    public static boolean isNull(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof Optional) {
            // 可以包含或不包含非空值的容器对象。如果存在值，isPresent（）将返回true，而get（）则返回该值
            return !((Optional<?>) object).isPresent();
        } else if (object instanceof CharSequence) {
            // char值的一个可读序列。实现类：CharBuffer、String、StringBuffer、StringBuilder等
            return ((CharSequence) object).length() == 0;
        } else if (object.getClass().isArray()) {
            // 数组
            return Array.getLength(object) == 0;
        } else if (object instanceof Collection) {
            // 集合
            return ((Collection<?>) object).isEmpty();
        } else if (object instanceof Map) {
            // 键值对集合
            return ((Map<?, ?>) object).isEmpty();
        }

        return false;
    }

    /**
     * * 判断一个对象是否为空
     *
     * @param object Object
     * @return true：非空 false：为空
     */
    public static boolean isNotNull(Object object)
    {
        return !isNull(object);
    }

    /**
     * 将object转化为string object必须为字符串类型
     */
    public static String convertToString(Object obj) {
        if (obj == null) {
            return EMPTY_STR;
        } else {
            return String.valueOf(obj).trim();
        }
    }

    /**
     * 若字符串为null返回空串，不为null返回源字符串
     */
    public static String convertNullToEmptyStr(String src) {
        return src == null ? "" : src;
    }

    /**
     * 字符串比较
     * @return 相同-true
     */
    public static boolean compareStr(String src, Object o) {
        if (src != null)
            return src.equals(o);
        else if (src == null && o ==null)
            return true;
        return false;
    }

    public static boolean containsAnyIgnoreCase(String str, String ... strings) {
        if (isEmpty(str)) return false;
        if (isNull(strings)) return false;

        for (String s : strings) {
            if (str.toLowerCase().equals(s.toLowerCase()))
                return true;
        }
        return false;
    }
}
