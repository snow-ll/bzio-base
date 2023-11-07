package org.bzio.common.core.util;

import org.bzio.common.core.config.BaseConstant;

import java.security.InvalidParameterException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 *
 * @author snow
 */
public class DateUtil {

    private static final int MONTHS_IN_A_YEAR = 12;

    // 确保SimpleDateFormat线程安全问题
    private static final ThreadLocal<SimpleDateFormat> THREAD_LOCAL = ThreadLocal.withInitial(() -> new SimpleDateFormat(BaseConstant.YYYY_MM_DD_HH_MM_SS));

    private static SimpleDateFormat getDateFormat() {
        return THREAD_LOCAL.get();
    }

    /**
     * 获取当前时间
     */
    public static Date getNowDate() {
        return new Date();
    }

    /**
     * 获取当前时间
     *  去除秒后面的时间戳
     */
    public static Date getNowDateToSeconds() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 清除毫秒部分
        LocalDateTime truncatedDateTime = now.withNano(0);
        
        // 转换为Date
        Date date = Date.from(truncatedDateTime.atZone(ZoneId.systemDefault()).toInstant());
        return date;
    }

    /**
     * 获取当前时间（精确到秒）
     */
    public static Date getNowDateAccurateSecond() {
        return accurateSecond(getNowDate());
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
     * 获取两个时间差之间所有的日期
     * @return
     */
    public static List<String> getDateBetween(Date startDate, Date endDate, int type, String pattern) {
        List<String> dateList = new ArrayList<>();
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        while (startCalendar.before(endCalendar) || startCalendar.equals(endCalendar)) {
            dateList.add(format(startCalendar.getTime(), pattern));
            startCalendar.add(type, 1);
        }

        return dateList;
    }

    public static void main(String[] args) {
        System.out.println(getDateBetween(parse("2023-09-01", BaseConstant.YYYY_MM_DD), parse("2025-12-31", BaseConstant.YYYY_MM_DD),
                Calendar.MONTH, BaseConstant.YYYY_MM));
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

    /**
     * 时间精确到秒
     * @return 精确到秒的时间
     */
    public static Date accurateSecond(Date date) {
        return parse(format(date));
    }

    public static void getCurrentDateComponents(Date date) {
        // 获取当前时间

        // 创建 Calendar 对象，并设置为当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        // 获取年份
        int year = calendar.get(Calendar.YEAR);
        // 获取月份（注意：月份从 0 开始计数，所以需要加 1）
        int month = calendar.get(Calendar.MONTH) + 1;
        // 获取日期
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        // 获取小时
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        // 获取分钟
        int minute = calendar.get(Calendar.MINUTE);
        // 获取秒钟
        int second = calendar.get(Calendar.SECOND);
        // 获取毫秒
        int milliSecond = calendar.get(Calendar.MILLISECOND);

        // 输出结果
        System.out.println("年：" + year);
        System.out.println("月：" + month);
        System.out.println("日：" + day);
        System.out.println("时：" + hour);
        System.out.println("分：" + minute);
        System.out.println("秒：" + second);
        System.out.println("毫秒：" + milliSecond);
    }
}
