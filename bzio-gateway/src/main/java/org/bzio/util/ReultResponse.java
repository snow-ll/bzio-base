package org.bzio.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.bzio.common.core.util.JsonUtil;
import org.bzio.common.core.web.entity.AjaxResult;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * 异常情况返回
 *
 * @author snow
 */
public class ReultResponse {

    public static Mono<Void> responseWrite(ServerWebExchange exchange, int httpStatus, AjaxResult result) {

        String resultStr;
        try {
            resultStr = JsonUtil.toJSONString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        if (httpStatus == 0) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR.value();
        }
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setAccessControlAllowCredentials(true);
        response.getHeaders().setAccessControlAllowOrigin("*");
        response.setStatusCode(HttpStatus.valueOf(httpStatus));
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        DataBufferFactory dataBufferFactory = response.bufferFactory();
        DataBuffer buffer = dataBufferFactory.wrap(resultStr.getBytes(Charset.defaultCharset()));
        return response.writeWith(Mono.just(buffer)).doOnError((error) -> DataBufferUtils.release(buffer));
    }
}
