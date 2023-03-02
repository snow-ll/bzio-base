package org.bzio.common.security.filter;

import org.bzio.common.core.util.AesUtil;
import org.bzio.common.core.util.JwtUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.config.AuthConfig;
import org.bzio.common.redis.service.StringRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.security.core.context.SecurityContextHolder.*;

/**
 * token认证过滤器
 *
 * @author: snow
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    @Resource
    private JwtUtil jwtUtil;
    @Resource
    private UserDetailsService userDetailsService;
    @Resource
    StringRedisService stringRedisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader(AuthConfig.header);
        if (StringUtil.isNotEmpty(authHeader)) {
            String authToken = stringRedisService.get(AuthConfig.prefix + AesUtil.decrypt(authHeader, AuthConfig.aesKey));
            String username = jwtUtil.getUserNameFromToken(authToken);
            if (StringUtil.isNotEmpty(username) && jwtUtil.validateToken(authToken, username)) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                log.info("authenticated user:{}", username);
                getContext().setAuthentication(authentication);
            } else {
                clearContext();
            }
        } else {
            clearContext();
        }
        filterChain.doFilter(request, response);
    }
}
