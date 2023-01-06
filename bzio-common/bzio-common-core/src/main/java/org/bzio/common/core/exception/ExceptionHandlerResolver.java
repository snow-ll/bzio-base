package org.bzio.common.core.exception;

import org.bzio.common.core.web.entity.AjaxResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 统一异常处理
 *
 * @author: snow
 */
@RestControllerAdvice
public class ExceptionHandlerResolver {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = BaseException.class)
    public AjaxResult handleException(BaseException e) {
        logger.error("调用接口异常，异常信息：", e);
        return AjaxResult.error(e.getMessage());
    }

    @ExceptionHandler(value = Exception.class)
    public AjaxResult handleException(Exception e) {
        logger.error("调用接口异常，异常信息：", e);
        return AjaxResult.error("接口服务异常");
    }
}
