package com.order;

import com.feign.clients.UserFeign;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFeignClients(clients = UserFeign.class)
@Import(com.feign.config.MyConfig.class)
public class Order_ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Order_ServerApplication.class, args);
    }

}
