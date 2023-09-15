package org.bzio.common.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * @author snow
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@RefreshScope
public class AuthConfig {

    public static String header;

    public static String prefix;

    public static String secret;

    public static Long expiration;

    public static String aesKey;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        AuthConfig.header = header;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        AuthConfig.prefix = prefix;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        AuthConfig.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        AuthConfig.expiration = expiration;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        AuthConfig.aesKey = aesKey;
    }
}
