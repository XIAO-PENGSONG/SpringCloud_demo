package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.user.service")
public class User_ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(User_ServerApplication.class, args);
    }

}
