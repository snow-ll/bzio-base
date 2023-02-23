package org.bzio.filter;

import io.netty.buffer.ByteBufAllocator;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.util.XssUtil;
import org.bzio.util.XssResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * xss过滤器
 *
 * @author snow
 */
@Component
public class XssFilter implements GlobalFilter, Ordered {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("----自定义防XSS攻击网关全局过滤器生效----");
        ServerHttpRequest serverHttpRequest = exchange.getRequest();
        HttpMethod method = serverHttpRequest.getMethod();
        String contentType = serverHttpRequest.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE);
        URI uri = exchange.getRequest().getURI();

        // 判断走get/post过滤方式
        boolean postFlag = (method == HttpMethod.POST || method == HttpMethod.PUT) && (MediaType.APPLICATION_FORM_URLENCODED_VALUE.equalsIgnoreCase(contentType) || MediaType.APPLICATION_JSON_VALUE.equals(contentType));

        // 过滤get请求
        if (method == HttpMethod.GET) {
            String rawQuery = uri.getRawQuery();
            if (StringUtil.isEmpty(rawQuery)) {
                return chain.filter(exchange);
            }
            log.info("原请求参数为：{}", rawQuery);

            // 执行sql注入校验清理
            boolean chkRet = XssUtil.requestKeyWordsCheck(rawQuery);

            // 如果存在sql注入，直接拦截请求
            if (chkRet) {
                log.error("请求【" + uri.getRawPath() + uri.getRawQuery() + "】参数中包含不允许sql的关键词, 请求拒绝");
                return setUnauthorizedResponse(exchange);
            }

            // 透传参数，不对参数做任何处理
            return chain.filter(exchange);
        } else if (postFlag) {
            // post请求时，如果是文件上传之类的请求，不修改请求消息体
            return DataBufferUtils.join(serverHttpRequest.getBody()).flatMap(d -> Mono.just(Optional.of(d))).defaultIfEmpty(Optional.empty()).flatMap(optional -> {
                // 取出body中的参数
                String bodyString = "";
                if (optional.isPresent()) {
                    byte[] oldBytes = new byte[optional.get().readableByteCount()];
                    optional.get().read(oldBytes);
                    bodyString = new String(oldBytes, StandardCharsets.UTF_8);
                }
                HttpHeaders httpHeaders = serverHttpRequest.getHeaders();

                // 执行XSS清理
                log.debug("{} - [{}] 请求参数：{}", method, uri.getPath(), bodyString);
                boolean chkRet = XssUtil.requestKeyWordsCheck(bodyString);

                //  如果存在sql注入,直接拦截请求
                if (chkRet) {
                    log.error("{} - [{}：{}] 参数：{}, 包含不允许sql的关键词，请求拒绝", method, uri.getPath(), bodyString);
                    return setUnauthorizedResponse(exchange);
                }

                ServerHttpRequest newRequest = serverHttpRequest.mutate().uri(uri).build();

                // 重新构造body
                byte[] newBytes = bodyString.getBytes(StandardCharsets.UTF_8);
                DataBuffer bodyDataBuffer = toDataBuffer(newBytes);
                Flux<DataBuffer> bodyFlux = Flux.just(bodyDataBuffer);

                // 重新构造header
                HttpHeaders headers = new HttpHeaders();
                headers.putAll(httpHeaders);
                // 由于修改了传递参数，需要重新设置CONTENT_LENGTH，长度是字节长度，不是字符串长度
                int length = newBytes.length;
                headers.remove(HttpHeaders.CONTENT_LENGTH);
                headers.setContentLength(length);
                headers.set(HttpHeaders.CONTENT_TYPE, "application/json;charset=utf8");
                // 重写ServerHttpRequestDecorator，修改了body和header，重写getBody和getHeaders方法
                newRequest = new ServerHttpRequestDecorator(newRequest) {
                    @Override
                    @Nonnull
                    public Flux<DataBuffer> getBody() {
                        return bodyFlux;
                    }

                    @Override
                    @Nonnull
                    public HttpHeaders getHeaders() {
                        return headers;
                    }
                };

                return chain.filter(exchange.mutate().request(newRequest).build());
            });
        } else {
            return chain.filter(exchange);
        }
    }

    // 自定义过滤器执行的顺序，数值越大越靠后执行，越小就越先执行
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    /**
     * 设置403拦截状态
     */
    private Mono<Void> setUnauthorizedResponse(ServerWebExchange exchange) {
        return XssResponse.responseWrite(exchange, HttpStatus.FORBIDDEN.value(), AjaxResult.error("request is forbidden, SQL keywords are not allowed in the parameters."));
    }

    /**
     * 字节数组转DataBuffer
     *
     * @param bytes 字节数组
     * @return DataBuffer
     */
    private DataBuffer toDataBuffer(byte[] bytes) {
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(ByteBufAllocator.DEFAULT);
        DataBuffer buffer = nettyDataBufferFactory.allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }
}
