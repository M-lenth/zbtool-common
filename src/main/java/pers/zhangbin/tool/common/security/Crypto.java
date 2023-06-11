package pers.zhangbin.tool.common.security;


import java.security.NoSuchAlgorithmException;

/**
 * Classname: Crypto <br>
 * Description: <p> 编码解码工具类 </p>  <br>
 *
 * @author zhangbin
 * @create 2021/12/17 11:22
 * @since JDK1.8
 */
public class Crypto {
    private Crypto() {
    }

    /**
     * 加密字符串
     *
     * @param input 需要加密的字符串
     * @return 加密后的串
     */
    public static String encode(String input) throws NoSuchAlgorithmException {
        return Md5Util.decode(Base64Util.decode(input));
    }

    /**
     * 判断是否两个字符串相等 需使用本类加密方法加密后才可比较
     *
     * @param plain  明文
     * @param cipher 密文
     * @return <p>true-相等 false-不等</p>
     */
    public static boolean equal(String plain, String cipher) throws NoSuchAlgorithmException {
        return cipher != null && cipher.equals(encode(plain));
    }

}
