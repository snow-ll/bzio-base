package org.bzio.common.core.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bzio.common.core.config.AuthConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt Token工具类
 *
 * @author snow
 */
@Component
public class JwtUtil {

    private final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private final String USERNAME_KEY = "user_name";

    /**
     * 根据用户信息生成token
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(USERNAME_KEY, username);
        return generateToken(claims);
    }

    /**
     * 从token中获取登录用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = StringUtil.convertToString(claims.get(USERNAME_KEY));
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @return true-有效
     */
    public boolean validateToken(String token, String username) {
        return username.equals(getUsernameFromToken(token)) && !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token, String username) {
        Claims claims = getClaimsFromToken(token);
        claims.setExpiration(DateUtil.getNowDate());
        return generateToken(claims);
    }

    /**
     * 从token中获取过期时间
     */
    public Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(generateExpirationDate()).signWith(SignatureAlgorithm.HS512, AuthConfig.secret).compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(AuthConfig.secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return DateUtil.getDate(System.currentTimeMillis() + AuthConfig.expiration);
    }

    /**
     * 判断token是否已经过期
     *
     * @return true-过期
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(DateUtil.getNowDate());
    }
}
