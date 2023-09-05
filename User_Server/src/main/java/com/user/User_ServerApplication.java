package com.user;

import com.feign.clients.OrderFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = OrderFeign.class)
public class User_ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(User_ServerApplication.class, args);
    }

}
