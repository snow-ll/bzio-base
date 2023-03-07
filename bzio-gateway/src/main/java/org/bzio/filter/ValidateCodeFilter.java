package org.bzio.filter;

import org.bzio.common.core.util.*;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.config.CaptchaProperties;
import org.bzio.service.ValidateCodeServiceImpl;
import org.bzio.util.XssResponse;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import javax.annotation.Resource;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 验证码过滤器
 *
 * @author snow
 */
@Component
public class ValidateCodeFilter extends AbstractGatewayFilterFactory<Object> {

    private final static String[] VALIDATE_URL = new String[]{"/login", "/register"};

    @Resource
    private CaptchaProperties captchaProperties;

    @Resource
    private ValidateCodeServiceImpl validateCodeService;

    private static final String CODE = "code";

    private static final String UUID = "uuid";

    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            // 非登录/注册请求或验证码关闭，不处理
            if (!StringUtil.containsAnyIgnoreCase(request.getURI().getPath(), VALIDATE_URL) || !captchaProperties.getEnabled()) {
                return chain.filter(exchange);
            }

            try {
                // 获取请求体参数
                String rspStr = resolveBodyFromRequest(request);
                Map<String, String> map = JsonUtil.jsonToObject(rspStr, Map.class);
                validateCodeService.checkCaptcha(map.get(CODE), map.get(UUID));
            } catch (Exception e) {
                // 异常处理
                return XssResponse.responseWrite(exchange, HttpStatus.FORBIDDEN.value(), AjaxResult.error(e.getMessage()));
            }
            return chain.filter(exchange);
        };
    }

    /**
     * 获取请求体
     */
    private String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
        Flux<DataBuffer> body = serverHttpRequest.getBody();
        AtomicReference<String> bodyRef = new AtomicReference<>();
        body.subscribe(buffer -> {
            CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
            bodyRef.set(charBuffer.toString());
        });
        return bodyRef.get();
    }
}
