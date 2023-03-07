package org.bzio.handler;

import org.bzio.common.core.exception.BaseException;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.service.ValidateCodeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 * @author snow
 */
@Component
public class ValidateCodeHandler implements HandlerFunction<ServerResponse> {

    @Resource
    private ValidateCodeServiceImpl validateCodeService;

    @Override
    public Mono<ServerResponse> handle(ServerRequest serverRequest) {
        AjaxResult ajax;
        try {
            ajax = validateCodeService.createCaptcha();
        } catch (BaseException e) {
            return Mono.error(e);
        }
        return ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(ajax));
    }
}
