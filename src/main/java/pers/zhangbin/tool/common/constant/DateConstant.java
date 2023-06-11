package pers.zhangbin.tool.common.constant;

/**
 * Classname: DateConstant <br>
 * Description: <p> 字符串格式化常用格式 </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/17 11:14
 * @since JDK1.8
 */
public class DateConstant {


    // 日期格式
    /**
     * yyyy-MM-dd
     * <p> 2021-12-17 </p>
     */
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * yyyy-MM-dd HH:mm:ss
     * <p> 2021-12-17 12:00:00 </p>
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     * <p> 2021-12-17 12:00:00.000 </p>
     */
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * yyyyMMdd
     * <p> 20211217 </p>
     */
    public static final String YYYYMMDD = "yyyyMMdd";
    /**
     * yyyyMMddHHmmss
     */
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    /**
     * yyyyMMddHHmmss
     */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmssSSS";

    public static final String YYYY_MM_DD_HH_MM_EM = "yyyy/MM/dd HH/mm";

    public static final Long SECOND = 1000L;
    public static final Long MINUTE = 60L * SECOND;
    public static final Long HOUR = 60L * MINUTE;
    public static final Long DAY = 24 * HOUR;
    public static final Long WEEK = 7 * DAY;
}
