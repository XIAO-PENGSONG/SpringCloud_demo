package com.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 开启服务发现
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class CloudSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudSentinelApplication.class, args);
    }

}
