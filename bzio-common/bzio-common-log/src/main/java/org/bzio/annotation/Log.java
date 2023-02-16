package org.bzio.annotation;

import java.lang.annotation.*;

/**
 * 自定义操作日志记录注解
 *
 * @author snow
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log
{
    /**
     * 模块
     */
    String title() default "";

    /**
     * 操作类型
     */
    String operatorType() default "";

    /**
     * 操作说明
     */
    String operatorDesc() default "";
}
