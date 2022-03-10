package com.zhangbin.tool.common.util;

/**
 * ClassName: CharUtils <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2022/3/10 13:15
 * @since JDK1.8
 */
public class CharUtils {


    /**
     * 判断字符是否大写
     *
     * @param c 字符
     * @return <p>true-大写 </p>
     */
    public static boolean isUpper(char c) {
        return c <= 'Z' && c >= 'A';
    }

    /**
     * 判断字符是否小写
     *
     * @param c 字符
     * @return <p>true-小写 </p>
     */
    public static boolean isLower(char c) {
        return c <= 'z' && c >= 'a';
    }

    /**
     * 判断是否字符
     *
     * @param c 字符
     * @return <p>true-是字符 false-不是字符</p>
     */
    public static boolean isChar(char c) {
        return isLower(c) || isUpper(c);
    }

}
