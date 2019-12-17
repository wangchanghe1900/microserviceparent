package cn.unicom.microservice.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

/**
 * @author 王长河
 * @create 2019-11-27 11:37
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class JwtOperator<main> {
    /**
     * 秘钥
     * - 默认iiijjjkkklllmmmnnnooopppqqqrrrssstttaaabbbcccdddeeefffggghhh
     */
    @Value("${jwt.secret:iiijjjkkklllmmmnnnooopppqqqrrrssstttaaabbbcccdddeeefffggghhh}")
    private String secret;
    /**
     * 有效期，单位秒
     * - 默认1800
     */
    @Value("${jwt.expire-time-in-second:1800}")
    private Long expirationTimeInSecond;

    /**
     * 从token中获取claim
     *
     * @param token token
     * @return claim
     */
    public Claims getClaimsFromToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(this.secret.getBytes())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            //log.error("token解析错误", e);
            throw new IllegalArgumentException("Token invalided.");
        }
    }

    /**
     * 获取token的过期时间
     *
     * @param token token
     * @return 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimsFromToken(token)
                .getExpiration();
    }

    /**
     * 判断token是否过期
     *
     * @param token token
     * @return 已过期返回true，未过期返回false
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 计算token的过期时间
     *
     * @return 过期时间
     */
    private Date getExpirationTime() {
        System.out.println("======="+this.expirationTimeInSecond);
        return new Date(System.currentTimeMillis() + this.expirationTimeInSecond * 1000);
    }

    /**
     * 为指定用户生成token
     *
     * @param claims 用户信息
     * @return token
     */
    public String generateToken(Map<String, Object> claims) {
        Date createdTime = new Date();
        Date expirationTime = this.getExpirationTime();


        byte[] keyBytes = secret.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(createdTime)
                .setExpiration(expirationTime)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * 判断token是否非法
     *
     * @param token token
     * @return 未过期返回true，否则返回false
     */
    public Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    public static void main(String[] args) {
        String encodedHeader = "eyJhbGciOiJIUzI1NiJ9";
        byte[] header = Base64.decodeBase64(encodedHeader.getBytes());
        System.out.println(new String(header));
        String encodedPayload = "eyJpZCI6IjEiLCJpYXQiOjE1NjU1ODk1NDEsImV4cCI6MTU2Njc5OTE0MX0";
        // 测试5: 解密Payload
        byte[] payload = Base64.decodeBase64(encodedPayload.getBytes());
        System.out.println(new String(payload));
    }

    public void generateToken() {
    }
}
