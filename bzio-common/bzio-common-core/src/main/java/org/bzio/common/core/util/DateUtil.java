package org.bzio.common.core.util;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author: snow
 */
public class DateUtil {

    private static final int MONTHS_IN_A_YEAR = 12;

    // 确保SimpleDateFormat线程安全问题
    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<>();

    /**
     * 获取SimpleDateFormat对象
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getDateFormat(String pattern) {
        SimpleDateFormat format = threadLocal.get();
        if (format == null) {
            format = new SimpleDateFormat(pattern);
            threadLocal.set(format);
        }
        return format;
    }

    /**
     * 获取当前时间
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取指定时间
     */
    public static Date getDate(Long time) {
        return new Date(time);
    }

    /**
     * 获取当前时间字符串形式
     *
     * @param format
     * @return
     */
    public static String getNowDateStr(String format) {
        return format(new Date(), format);
    }

    /**
     * 时间类型转字符串
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        return getDateFormat(pattern).format(date);
    }

    /**
     * 字符串转时间类型
     *
     * @param date
     * @param pattern
     * @return
     */
    public static Date parse(String date, String pattern) {
        try {
            return getDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算两个时间相差月数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int getMonthDiff(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new InvalidParameterException("date1 and date2 cannot be null!");
        }
        if (date1.after(date2)) {
            throw new InvalidParameterException("date1 cannot be after date2!");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date1);
        int year1 = calendar.get(Calendar.YEAR);
        int month1 = calendar.get(Calendar.MONTH);
        int day1 = calendar.get(Calendar.DATE);

        calendar.setTime(date2);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DATE);

        int months = 0;
        if (day2 >= day1) {
            months = month2 - month1;
        } else {
            months = month2 - month1 - 1;
        }
        return (year2 - year1) * MONTHS_IN_A_YEAR + months;
    }
}
