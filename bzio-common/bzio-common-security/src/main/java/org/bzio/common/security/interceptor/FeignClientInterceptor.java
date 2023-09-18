package org.bzio.common.security.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.bzio.common.core.config.AuthConfig;
import org.bzio.common.core.util.AesUtil;
import org.bzio.common.security.util.AuthUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
            template.header(authConfig.getHeader(), AesUtil.encrypt(AuthUtil.getKey(), authConfig.getAesKey()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
