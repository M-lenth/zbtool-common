package com.zhangbin.tool.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.zhangbin.tool.common.constant.DateConstant.*;

/**
 * Classname: DateUtil <br>
 * Description: <p> 日期工具类 </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/17 11:13
 * @since JDK1.8
 */
public class DateUtil {

    /**
     * 根据格式化的日期字符串获取日期对象
     *
     * @param date    日期字符串
     * @param pattern 转换格式
     * @return 日期对象
     */
    public static Date getDate(String date, String pattern) throws ParseException {
        SimpleDateFormat sdf = getFormat(pattern);
        return sdf.parse(date);
    }

    /**
     * 根据格式化的日期字符串获取日期对象 使用格式 <p> yyyy-MM-dd HH:mm:ss </p>
     *
     * @param date 日期字符串
     * @return 日期对象
     */
    public static Date getDate(String date) throws ParseException {
        return getDate(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取日期格式化后的字符串
     *
     * @param date    目标日期
     * @param pattern 转换格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat format = getFormat(pattern);
        return format.format(date);
    }

    /**
     * 获取日期格式化后的字符串 使用默认格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date 目标日期
     * @return 格式化后的字符串
     */
    public static String format(Date date) {
        return format(date, YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取SimpleDateFormat
     *
     * @param pattern 转换格式
     * @return SimpleDateFormat对象
     * @see SimpleDateFormat
     */
    private static SimpleDateFormat getFormat(String pattern) {
        return new SimpleDateFormat(pattern);
    }

    /**
     * 上一年年份
     *
     * @param date 指定日期
     * @return 上一年年份
     */
    public static String getLastYear(Date date) {
        Calendar cale = getCalendar(date);
        return String.valueOf(cale.get(Calendar.YEAR) - 1);
    }

    /**
     * 上一年年份
     *
     * @param date 指定日期字符串
     * @return 上一年年份
     */
    public static String getLastYear(String date) throws ParseException {
        return getLastYear(getDate(date));
    }

    /**
     * 上一年年份
     *
     * @param date    指定日期字符串
     * @param pattern 日期转换格式
     * @return 上一年年份
     */
    public static String getLastYear(String date, String pattern) throws ParseException {
        return getLastYear(getDate(date, pattern));
    }


    /**
     * 获取下一年年份
     *
     * @param date 指定日期
     * @return 下一年年份
     */
    public static String getNextYear(Date date) {
        return String.valueOf(getCalendar(date).get(Calendar.YEAR) + 1);
    }

    /**
     * 获取下一年年份 输入区日期格式默认为yyyy-MM-dd
     *
     * @param date 指定日期字符串
     * @return 下一年年份
     */
    public static String getNextYear(String date) throws ParseException {
        return getNextYear(getDate(date));
    }

    /**
     * 获取下一年年份 输入区日期格式默认为yyyy-MM-dd
     *
     * @param date    指定日期字符串
     * @param pattern 日期转换格式
     * @return 下一年年份
     */
    public static String getNextYear(String date, String pattern) throws ParseException {
        return getNextYear(getDate(date, pattern));
    }

    /**
     * 获取指定日期的上一个月字符串显示 格式为yyyy-MM
     *
     * @param date 指定日期
     * @return yyyy-MM 如: 2022-12 上一个月显示: 2022-11
     * 又: 2022-1 => 2021-12
     */
    public static String getLastMonth(Date date) {
        Calendar cale = getCalendar(date);
        int month = cale.get(Calendar.MONTH) + 1;
        int year = cale.get(Calendar.YEAR);
        int lastMonth;
        if (month == 1) {
            lastMonth = 12;
            year--;
        } else {
            lastMonth = month - 1;
        }
        return year + "-" + lastMonth;
    }

    /**
     * 获取指定日期的上一个月字符串显示 格式为yyyy-MM
     *
     * @param date 指定日期 默认格式 yyyy-MM-dd
     * @return yyyy-MM 如: 2022-12 上一个月显示: 2022-11
     * 又: 2022-1 => 2021-12
     */
    public static String getLastMonth(String date) throws ParseException {
        return getLastMonth(getDate(date));
    }

    /**
     * 获取指定日期的上一个月字符串显示 格式为yyyy-MM
     *
     * @param date    指定日期
     * @param pattern 日期转换格式
     * @return yyyy-MM 如: 2022-12 上一个月显示: 2022-11
     * 又: 2022-1 => 2021-12
     */
    public static String getLastMonth(String date, String pattern) throws ParseException {
        return getLastMonth(getDate(date, pattern));
    }


    /**
     * 获取下一个月的月份
     *
     * @param date 指定日期
     * @return 2022-10 => 2022-11 又: 2021-12 => 2022-1
     */
    public static String getNextMonth(Date date) {
        Calendar cale = getCalendar(date);
        int month = cale.get(Calendar.MONTH) + 1;
        int year = cale.get(Calendar.YEAR);
        if (month == 12) {
            return (year + 1) + "-1";
        } else {
            return year + "-" + (++month);
        }
    }

    /**
     * 获取下一个月的月份
     *
     * @param date 指定日期 默认格式yyyy-MM-dd
     * @return 2022-10 => 2022-11 又: 2021-12 => 2022-1
     */
    public static String getNextMonth(String date) throws ParseException {
        return getNextMonth(getDate(date));
    }

    /**
     * 获取下一个月的月份
     *
     * @param date    指定日期
     * @param pattern 日期转换格式
     * @return 2022-10 => 2022-11 又: 2021-12 => 2022-1
     */
    public static String getNextMonth(String date, String pattern) throws ParseException {
        return getNextMonth(getDate(date, pattern));
    }

    /**
     * 获取指定日期的上一天 使用yyyy-MM-dd
     *
     * @param date 指定日期
     * @return 如: 2022-3-18 => 2022-3-17
     * 又: 2022-3-1
     */
    public static String getLastDay(Date date) {
        Calendar cale = getCalendar(date);
        int day = cale.get(Calendar.DATE);
        String result;
        if (day == 1) {
            String lastMonth = getLastMonth(date);
            int month = Integer.parseInt(lastMonth.split("-")[1]);
            if (isLargeMonth(month)) {
                // 1 3 5 7 8 10 12月
                day = 31;
                result = lastMonth + "-" + day;
            } else {
                // 2 4 6 9 11
                int year = cale.get(Calendar.YEAR);
                if (month == 2) {
                    day = prime(year) ? 29 : 28;
                } else {
                    day = 30;
                }
                result = year + "-" + month + "-" + day;
            }

        } else {
            day--;
            result = cale.get(Calendar.YEAR) + "-" + (cale.get(Calendar.MONTH) + 1) + "-" + day;
        }
        return result;
    }

    /**
     * 获取指定日期的上一天 使用yyyy-MM-dd
     *
     * @param date 指定日期
     * @return 如: 2022-3-18 => 2022-3-17
     * 又: 2022-3-1
     */
    public static String getLastDay(String date) throws ParseException {
        return getLastDay(getDate(date));
    }

    /**
     * 获取指定日期的上一天 使用yyyy-MM-dd
     *
     * @param date    指定日期
     * @param pattern 日期转换格式
     * @return 如: 2022-3-18 => 2022-3-17
     * 又: 2022-3-1
     */
    public static String getLastDay(String date, String pattern) throws ParseException {
        return getLastDay(getDate(date, pattern));
    }

    /**
     * 获取指定日期下一天
     *
     * @param date 指定日期
     * @return yyyy-MM-dd 如: 2020-2-29 => 2020-3-1; 2020-2-28 => 2020-2-29 ; 2020-12-31 => 2021-1-1
     */
    public static String getNextDay(Date date) {
        Calendar cale = getCalendar(date);
        int year = cale.get(Calendar.YEAR);
        int month = cale.get(Calendar.MONTH) + 1;
        int day = cale.get(Calendar.DATE);
        if (month == 2) {
            if (prime(year)) {
                if (day == 29) {
                    return year + "-3-1";
                }
            } else {
                if (day == 28) {
                    return year + "-3-1";
                }
            }
        } else {
            if (isLargeMonth(month)) {
                if (day == 31) {
                    if (month == 12) {
                        return ++year + "-1-1";
                    } else {
                        return year + "-" + ++month + "-1";
                    }
                }
            } else {
                if (day == 30) {
                    return year + "-" + ++month + "-" + day;
                }
            }
        }
        return year + "-" + month + "-" + ++day;
    }

    /**
     * 获取指定日期下一天
     *
     * @param date 指定日期
     * @return yyyy-MM-dd 如: 2020-2-29 => 2020-3-1; 2020-2-28 => 2020-2-29 ; 2020-12-31 => 2021-1-1
     */
    public static String getNextDay(String date) throws ParseException {
        return getNextDay(getDate(date));
    }

    /**
     * 获取指定日期下一天
     *
     * @param date    指定日期
     * @param pattern 日期转换格式
     * @return yyyy-MM-dd 如: 2020-2-29 => 2020-3-1; 2020-2-28 => 2020-2-29 ; 2020-12-31 => 2021-1-1
     */
    public static String getNextDay(String date, String pattern) throws ParseException {
        return getNextDay(getDate(date, pattern));
    }

    /**
     * 是否闰年
     *
     * @param n 年份
     * @return true-是闰年 false-不是闰年
     */
    public static boolean prime(int n) {
        return ((n % 4 == 0 && n % 100 != 0) || n % 400 == 0);
    }

    /**
     * 是否大月
     *
     * @param month 月份
     * @return 月份1 3 5 7 8 10 12月为大月，其余算小月
     */
    public static boolean isLargeMonth(int month) {
        Set<Integer> sets = ListUtil.newSets(1, 3, 5, 7, 8, 10, 12);
        return sets.contains(month);
    }

    private static Calendar getCalendar(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance;
    }

}
