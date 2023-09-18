package org.bzio.common.security.exception;

import org.bzio.common.core.exception.BaseException;
import org.bzio.common.core.web.entity.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author snow
 */
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SecurityExceptionHandlerResolver {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = BaseException.class)
    public AjaxResult handleException(BaseException e) {
        log.error("调用接口异常，异常信息：", e);
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public AjaxResult handleException(AccessDeniedException e) {
        log.error("调用接口异常，异常信息：", e);
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public AjaxResult handleException(Exception e) {
        log.error("调用接口异常，异常信息：", e);
        return AjaxResult.error("接口服务异常");
    }
}
