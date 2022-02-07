package com.zhangbin.tool.common.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Classname: Base64Util <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/22 10:34
 * @since JDK1.8
 */
public class Base64Util {

    private Base64Util() {
    }

    /**
     * Base64编码
     *
     * @param input 字符串
     * @return 编码后字符串
     */
    public static String encode(String input) {
        return encode(input.getBytes());
    }

    /**
     * Base64编码
     *
     * @param input 字节数组
     * @return 编码后字节数组
     */
    public static String encode(byte[] input) {
        byte[] bytes = Base64.getEncoder().encode(input);
        return new String(bytes);
    }

    /**
     * Base64解码
     *
     * @param input 编码后字符串
     * @return 源字符串
     */
    public static String decode(String input) {
        return new String(decodeBytes(input));
    }

    /**
     * Base64解码
     *
     * @param input 编码后字符串
     * @return 原字节数组
     */
    public static byte[] decodeBytes(String input) {
        if (null == input) {
            return null;
        }
        return Base64.getDecoder().decode(input.getBytes(StandardCharsets.UTF_8));
    }
}
