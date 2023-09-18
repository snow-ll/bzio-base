package org.bzio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 认证中心
 */
@EnableFeignClients
@SpringBootApplication
@ComponentScan(value = "org.bzio.*")
public class AuthApplication {
    public static void main( String[] args ) {
        SpringApplication.run(AuthApplication.class);
        System.out.println("启动成功!");
    }
}
