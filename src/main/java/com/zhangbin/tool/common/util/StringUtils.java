package com.zhangbin.tool.common.util;

import java.nio.charset.StandardCharsets;

import static com.zhangbin.tool.common.util.CharUtils.isUpper;

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


    /**
     * 下载保存时中文文件名的字符编码转换方法
     */
    public static String toUTF8String(String str) {
        StringBuilder sb = new StringBuilder();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            // 取出字符中的每个字符
            char c = str.charAt(i);
            // Unicode码值为0~255时，不做处理
            if (c <= 255) {
                sb.append(c);
            } else { // 转换 UTF-8 编码
                byte[] b;
                b = Character.toString(c).getBytes(StandardCharsets.UTF_8);
                // 转换为%HH的字符串形式
                for (int value : b) {
                    int k = value;
                    if (k < 0) {
                        k &= 255;
                    }
                    sb.append("%").append(Integer.toHexString(k).toUpperCase());
                }
            }
        }
        return sb.toString();
    }

    /**
     * 下划线转驼峰命名
     *
     * @param str 输入的原串
     * @return 转换后的字符串
     */
    public static String underLineToHump(String str) {
        StringBuilder builder = new StringBuilder();
        boolean flag = false;
        for (char c : str.toCharArray()) {
            if (c == '_') {
                flag = true;
                continue;
            }
            if (flag) {
                String upperCase = (c + "").toUpperCase();
                builder.append(upperCase);
                flag = false;
                continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }

    /**
     * 驼峰转下划线
     *
     * @param str 原串
     * @return 下划线表示的字符串
     */
    public static String humpToUnderLine(String str) {
        StringBuilder builder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (isUpper(c)) {
                builder.append("_").append((char) (c - 'A' + 'a'));
                continue;
            }
            builder.append(c);
        }
        return builder.toString();
    }

}
