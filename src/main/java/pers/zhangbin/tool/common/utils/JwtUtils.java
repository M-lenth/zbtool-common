package pers.zhangbin.tool.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * ClassName: <p> JwtUtils  </p>
 * CreateTime: 2023/8/7 21:59
 * Description: <p> 生成解析JWT的工具类  </p>
 *
 */
public class JwtUtils {
    /**
     * JWT默认有效期1h
     */
    private static final Long JWT_TTL = 3600000L;

    /**
     * 生成令牌
     *
     * @param id      唯一Id
     * @param subject JWT主题
     * @param issuer  签发人
     * @param ttMills 有效时间
     * @param claims  Token中携带的数据
     * @param jwtKey  jks证书
     * @return JWT字符串
     */
    public static String createJwt(String id, String subject, String issuer, Long ttMills, Map<String, Object> claims, String jwtKey) {
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
        Date expDate  = new Date(expMills);
        //生成密钥
        SecretKey secretKey = generaKey(jwtKey);
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
        // 放入claim值
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            builder.claim(entry.getKey(), entry.getValue());
        }
        return builder.compact();
    }

    /**
     * 生成密钥
     *
     * @return 密钥
     */
    private static SecretKey generaKey(String jwtKey) {
        byte[] encodeKey = Base64.getEncoder().encode(jwtKey.getBytes());
        return new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
    }

    /**
     * 解析令牌
     *
     * @param jwt JWT的内容
     * @return 原数据
     */
    public static Claims parseJwt(String jwt, String jwtKey) {
        SecretKey secretKey = generaKey(jwtKey);
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwt).getBody();
    }

}
