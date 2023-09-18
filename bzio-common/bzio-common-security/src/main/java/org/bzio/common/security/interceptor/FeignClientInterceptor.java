package org.bzio.common.security.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.bzio.common.core.config.AuthConfig;
import org.bzio.common.core.util.AesUtil;
import org.bzio.common.security.util.AuthUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author snow
 */
@Component
public class FeignClientInterceptor implements RequestInterceptor {

    @Resource
    AuthConfig authConfig;

    @Override
    public void apply(RequestTemplate template) {
        try {
            // 获取对象
            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                    .getRequestAttributes();
            if (requestAttributes != null) {
                // 获取请求对象
                HttpServletRequest request = requestAttributes.getRequest();
                if ("/login".equals(request.getRequestURI())) {
                    template.header(authConfig.getHeader(), AesUtil.encrypt(AuthUtil.getKey(), authConfig.getAesKey()));
                } else {
                    template.header(authConfig.getHeader(), request.getHeader(authConfig.getHeader()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
