package org.bzio.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Pattern;

/**
 * xss注入校验类
 */
public class XssUtil {

    private static final Logger logger = LoggerFactory.getLogger(XssUtil.class);

    private static final String BADSTR_REG = "\\b(and|or)\\b.{1,6}?(=|>|<|\\bin\\b|\\blike\\b)|\\/\\*.+?\\*\\/|<\\s*script\\b|\\bEXEC\\b|UNION.+?SELECT|UPDATE.+?SET|INSERT\\s+INTO.+?VALUES|(SELECT|DELETE).+?FROM|(CREATE|ALTER|DROP|TRUNCATE)\\s+(TABLE|DATABASE)";

    private static final Pattern SQL_PATTERN = Pattern.compile(BADSTR_REG, Pattern.CASE_INSENSITIVE);//整体都忽略大小写


    /**
     * get请求sql注入校验
     */
    public static boolean requestKeyWordsCheck(String value) {
        //参数需要url编码
        //这里需要将参数转换为小写来处理
        //不改变原值
        //value示例 order=asc&pageNum=1&pageSize=100&parentId=0
        String lowerValue;
        try {
            lowerValue = URLDecoder.decode(value, "UTF-8").toLowerCase();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        //获取到请求中所有参数值-取每个key=value组合第一个等号后面的值
        if (SQL_PATTERN.matcher(lowerValue).find()) {
            logger.error("参数[{}]中包含不允许sql的关键词", lowerValue);
            return true;
        }
        return false;
    }
}