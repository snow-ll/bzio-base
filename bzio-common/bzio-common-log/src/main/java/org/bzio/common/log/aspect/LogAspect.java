package org.bzio.common.log.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.bzio.common.log.annotation.Log;
import org.bzio.common.core.enums.BusinessStatus;
import org.bzio.common.core.util.*;
import org.bzio.common.security.entity.SysLog;
import org.bzio.common.security.mapper.SysLogMapper;
import org.bzio.common.security.util.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 日志处理切面
 *
 * @author snow
 */
@Aspect
@Component
public class LogAspect {

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Resource
    private SysLogMapper sysLogMapper;

    /**
     * 切点
     */
    @Pointcut("@annotation(org.bzio.common.log.annotation.Log)")
    public void logPointcut() {

    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "logPointcut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "logPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    private void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        // 获得自定义日志注解
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            Log annotationLog = method.getAnnotation(Log.class);
            if (StringUtil.isNull(annotationLog)) return;

            // 保存日志
            HttpServletRequest request = ServletUtil.getRequest();
            String requestURI = request.getRequestURI();

            SysLog sysLog = new SysLog();
            sysLog.setLogId(IdUtil.snowflakeId());
            sysLog.setTitle(annotationLog.title());
            sysLog.setBusinessType(annotationLog.businessType().ordinal());
            sysLog.setLogDesc(annotationLog.logDesc());
            sysLog.setOperator(AuthUtil.getUsername());
            sysLog.setOperationUrl(requestURI);
            sysLog.setOperationIp(ServletUtil.getIpAddr());
            sysLog.setOperationTime(DateUtil.getNowDate());
            sysLog.setRequestMethod(request.getMethod());

            if (annotationLog.isRequest() && StringUtil.isNotNull(joinPoint.getArgs())) {
                sysLog.setRequestParam(JsonUtil.toJSONString(joinPoint.getArgs()));
            }

            if (annotationLog.isResponse()) {
                sysLog.setResponseResult(JsonUtil.toJSONString(jsonResult));
            }
            sysLog.setStatus(BusinessStatus.SUCCESS.ordinal());

            if (StringUtil.isNotNull(e)) {
                sysLog.setErrorMsg(e.getMessage());
                sysLog.setStatus(BusinessStatus.FAIL.ordinal());
            }
            sysLogMapper.insert(sysLog);
        }
    }
}
