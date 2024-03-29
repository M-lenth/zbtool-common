package pers.zhangbin.tool.common.utils;

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

    /**
     * 判断字符是否是数字
     *
     * @param c 字符
     * @return true-是数字 false-不是数字
     */
    public static boolean isNum(char c) {
        return c <= '9' && c >= '0';
    }

    /**
     * 是否特殊字符
     *
     * @param c 字符
     * @return true-是特殊字符 false-不是特殊字符
     */
    public static boolean isSpecial(char c) {
        return !isNum(c) && !isChar(c);
    }

}
