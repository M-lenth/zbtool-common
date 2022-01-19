package com.zhangbin.tool.common.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classname: Md5Util <br>
 * Description: <p>  </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/22 10:34
 * @since JDK1.8
 */
public class Md5Util {

    private static final char[] HEX_DIGITS =
        {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * MD5加密
     *
     * @param input MD5
     * @return 加密后字符串
     */
    public static String decode(String input) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(input.getBytes(StandardCharsets.UTF_8));
            byte[] digest = messageDigest.digest();
            int j = digest.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte b : digest) {
                str[k++] = HEX_DIGITS[b >>> 4 & 15];
                str[k++] = HEX_DIGITS[b & 15];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5算法异常");
        }

    }

}
