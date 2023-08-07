package pers.zhangbin.tool.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * ClassName: JwtUtil <br>
 * Description: <p> 生成解析JWT的工具类 </p>  <br>
 *
 * @author zhangbin
 * @create 2022/2/10 14:12
 * @since JDK1.8
 */
public class JwtUtil {

    /**
     * JWT默认有效期1h
     */
    private static final Long JWT_TTL = 3600000L;
    /**
     * 生成JWT需要的Key
     */
    private final String JWT_KEY;

    public JwtUtil(String jwtKey) {
        JWT_KEY = jwtKey;
    }


    /**
     * 生成令牌
     *
     * @param id      唯一Id
     * @param subject JWT主题
     * @param issuer  签发人
     * @param ttMills 有效时间
     * @return JWT字符串
     */
    public String createJwt(String id, String subject, String issuer, Long ttMills) {
        //指定算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前系统时间
        long nowMills = System.currentTimeMillis();
        //令牌签发时间
        Date now = new Date(nowMills);
        //如果令牌有效期为null，则默认的设置有效值为1小时
        if (ttMills == null) {
            ttMills = JWT_TTL;
        }
        //过期时间
        long expMills = nowMills + ttMills;
        Date expDate = new Date(expMills);
        //生成密钥
        SecretKey secretKey = generaKey();
        //封装JWT的信息
        JwtBuilder builder = Jwts.builder()
            //唯一的ID
            .setId(id)
            //主题
            .setSubject(subject)
            //签发者
            .setIssuer(issuer)
            //签发时间
            .setIssuedAt(now)
            //签名
            .signWith(signatureAlgorithm, secretKey)
            //设置过期时间
            .setExpiration(expDate);
        return builder.compact();
    }

    /**
     * 生成密钥
     *
     * @return 密钥
     */
    private SecretKey generaKey() {
        byte[] encodeKey = Base64.getEncoder().encode(JWT_KEY.getBytes());
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
    }

    /**
     * 解析令牌
     *
     * @param jwt    JWT的内容
     * @param jwtKey 生成JWT时的使用的Key值
     * @return 原数据
     */
    public Claims parseJwt(String jwt, String jwtKey) {
        SecretKey secretKey = generaKey();
        return Jwts.parser()
            .setSigningKey(secretKey)
            .parseClaimsJws(jwt)
            .getBody();
    }

}
