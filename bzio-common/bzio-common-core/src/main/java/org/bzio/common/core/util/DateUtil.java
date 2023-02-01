package org.bzio.common.core.util;

import org.bzio.common.core.config.BaseConstant;

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
    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 获取SimpleDateFormat对象
     */
    private static SimpleDateFormat getDateFormat() {
        SimpleDateFormat format = THREAD_LOCAL.get();
        if (format == null) {
            format = new SimpleDateFormat(BaseConstant.YYYY_MM_DD_HH_MM_SS);
            THREAD_LOCAL.set(format);
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
     */
    public static String getNowDateStr(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 时间类型转字符串
     * 默认yyyy-MM-dd HH:mm:ss格式
     */
    public static String format(Date date) {
        return format(date, BaseConstant.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 时间类型转字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = getDateFormat();
        simpleDateFormat.applyPattern(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 字符串转时间类型
     * 默认yyyy-MM-dd HH:mm:ss格式
     */
    public static Date parse(String date) {
        return parse(date, BaseConstant.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 字符串转时间类型
     */
    public static Date parse(String date, String pattern) {
        try {
            SimpleDateFormat simpleDateFormat = getDateFormat();
            simpleDateFormat.applyPattern(pattern);
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 计算两个时间相差月数
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

        int months;
        if (day2 >= day1) {
            months = month2 - month1;
        } else {
            months = month2 - month1 - 1;
        }
        return (year2 - year1) * MONTHS_IN_A_YEAR + months;
    }

    /**
     * 判断时间是否处于某个时间段内
     *
     * @param date 需要比较的时间
     * @param start 起始时间
     * @param end 结束时间
     */
    public static boolean belongCalendar(Date date, Date start, Date end) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Calendar after = Calendar.getInstance();
        after.setTime(start);
        Calendar before = Calendar.getInstance();
        before.setTime(end);

        return c.after(after) && c.before(before);
    }
}
