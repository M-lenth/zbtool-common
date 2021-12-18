package com.zhangbin.tool.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.zhangbin.tool.constant.DateConstant.*;

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

}
