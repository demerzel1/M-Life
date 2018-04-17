package com.dhu.utils;

/**
 * Created by demerzel on 2018/4/17.
 */
import java.security.KeyPair;
import java.util.Date;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.RsaProvider;
/**
 * API调用认证工具类，采用RSA加密
 *
 */
public class JWTUtils {
    static KeyPair key = RsaProvider.generateKeyPair(1024);
    /**
     * 获取Token
     * @param uid 用户ID
     * @param exp 失效时间，单位分钟
     * @return
     */
    public static String getToken(String uid, int exp) {
        long endTime = new Date().getTime() + 1000 * 60 * exp;
        return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.RS512, key.getPrivate()).compact();
    }
    /**
     * 获取Token
     * @param uid 用户ID
     * @param exp 默认失效时间为1天
     * @return
     */
    public static String getToken(String uid) {
        long endTime = new Date().getTime() + 1000 * 60 * 1440;
        return Jwts.builder().setSubject(uid).setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.RS512, key.getPrivate()).compact();
    }
    /**
     * 检查Token是否合法
     * @param token
     * @return JWTResult
     */
    public static JWTResult checkToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(key.getPublic()).parseClaimsJws(token).getBody();
            String sub = claims.get("sub", String.class);
            return new JWTResult(true, sub, "合法请求");
        } catch (ExpiredJwtException e) {
            // 在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
            return new JWTResult(false, null, "token已过期");
        } catch (SignatureException e) {
            // 在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
            return new JWTResult(false, null, "非法请求");
        } catch (Exception e) {
            return new JWTResult(false, null, "非法请求");
        }
    }

}