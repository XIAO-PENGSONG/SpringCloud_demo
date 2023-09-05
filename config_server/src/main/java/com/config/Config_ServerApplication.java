package com.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
// @RefreshScope // 支持Config配置刷新自动读取
public class Config_ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Config_ServerApplication.class,args);
    }

}
