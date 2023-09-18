package org.bzio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 系统模块
 */
@EnableFeignClients
@SpringBootApplication
@ComponentScan(value = "org.bzio.*")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class);
        System.out.println("启动成功！");
    }
}
