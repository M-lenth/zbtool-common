package com.zhangbin.tool.common;

import java.util.Base64;

/**
 * Classname: Crypto <br>
 * Description: <p> 编码解码工具类 </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/17 11:22
 * @since JDK1.8
 */
public class Crypto {
    /**
     * 获取base64的加密数据
     *
     * @param input 加密原数据
     * @return 加密后数据
     */
    public static String base64Encode(byte[] input) {
        byte[] bytes = Base64.getEncoder().encode(input);
        return new String(bytes);
    }

    /**
     * base64解码数据
     *
     * @param input 加密后数据
     * @return 解码后原数据
     */
    public static byte[] base64Decode(String input) {
        return Base64.getDecoder().decode(input.getBytes());
    }

}
