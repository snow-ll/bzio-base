package org.bzio.common.log.annotation;

import org.bzio.common.core.enums.BusinessType;

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
     * 操作业务模块
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作说明
     */
    String logDesc() default "";
}
