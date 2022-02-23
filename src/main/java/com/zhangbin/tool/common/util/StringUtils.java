package com.zhangbin.tool.common.util;

/**
 * Classname: StringUtils <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/18 11:22
 * @since JDK1.8
 */
public class StringUtils {
    /**
     * 字符串是否为空
     *
     * @param str 判断的目标字符串
     * @return <p>true 空 false 不为空</p>
     */
    public static boolean isEmpty(String str) {
        return null == str || "".equals(str);
    }

    /**
     * 字符串是否都为空
     *
     * @param str 判断的字符串序列
     * @return <p>true 只要有一个不为空，则返回true; false 全部不为空返回false</p>
     */
    public static boolean isEmptyOr(String... str) {
        for (String s : str) {
            if (!isEmpty(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串是否都不为空
     *
     * @param str 判断的字符串序列
     * @return <p>false 只要有一个为空，则返回false; true 全部不为空返回true</p>
     */
    public static boolean isEmptyAnd(String... str) {
        for (String s : str) {
            if (isEmpty(s)) {
                return false;
            }
        }
        return true;
    }

}
