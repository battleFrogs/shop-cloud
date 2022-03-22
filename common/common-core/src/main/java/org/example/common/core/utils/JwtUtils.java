package org.example.common.core.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * JWT 工具类
 */
public class JwtUtils {


    /**
     * SECRET 密钥
     */
    private final static String SECRET = "JWT-GJC";

    /**
     * 身份信息
     */
    public final static String USE_ID = "userId";
    public final static String USE_NAME = "userName";


    /**
     * 生成Token
     */
    public static String createAccessToken(Long useId, String userName) {
        // 登陆成功生成JWT
        return Jwts.builder()
                // 自定义属性
                .claim(USE_ID, useId)
                .claim(USE_NAME, userName)
                // 签名算法和密钥
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    /**
     * 解析token值
     *
     * @param token token
     * @return claims
     */
    public static Claims parseToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }


    /**
     * 获取用户Id信息
     *
     * @param token Token值
     * @return useId  具体useId值
     */
    public static String getUseId(String token) {
        Claims claims = parseToken(token);
        return getValue(claims, USE_ID);
    }

    /**
     * 获取用户Id信息
     *
     * @param token Token值
     * @return useName  具体useName值
     */
    public static String getUseName(String token) {
        Claims claims = parseToken(token);
        return getValue(claims, USE_NAME);
    }


    /**
     * 获取具体key的value至
     *
     * @param claims 身份信息
     * @param key    具体的key
     * @return 具体的value
     */
    private static String getValue(Claims claims, String key) {
        return claims.get(key) == null ? "" : (String) claims.get(key);
    }

}
