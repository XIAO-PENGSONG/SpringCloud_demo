package com.user.controller;

import com.feign.clients.OrderFeign;
import com.feign.entity.Order;
import com.feign.entity.User;
import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
//@RefreshScope
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final OrderFeign orderFeign;

    @Value("${server.port}")
    private int port;
//    @Value("${pattern.envSharedValue}")
//    private String envSharedValue;

    @RequestMapping("{id}")
    private User getId1(@PathVariable Integer id) {
        System.out.println("id===>>>" + id);
        User user = userService.getById(id);
        user.setPassword("User端口号===>>> 【" + port + "】");
        return user;
        //return new User(1001, "user", "来自端口号：【" + port + "】;envSharedValue:【"+envSharedValue+"】", 1000.0);

    }

    @RequestMapping("getOrder/{id}")
    public String getOrder(@PathVariable Integer id){
        System.out.println("id===>>>" + id);
        User user = userService.getById(id);
        Order order = orderFeign.getOrder1(id);
        return user.toString()+"  ======  "+order.toString();
    }

    @RequestMapping("api/{id}")
    public User id(@PathVariable int id) {
        if (Math.random() > 0.5) {
            // throw new RuntimeException("error");
            System.out.println(1/0);
        }
        return new User(id, "来自端口：【" + port + "】", "123", 100.0);
    }

    @RequestMapping("all")
    public List<User> users() {
        if (Math.random() > 0.5) {
            throw new RuntimeException("报错了！！！");
        }
        ThreadLocalRandom random = ThreadLocalRandom.current();
        List<User> users = new ArrayList<>();
        for (int i = 0; i < random.nextInt(5); i ++) {
            users.add(new User(8000 + i, "来自端口：【" + port + "】", "8k", 100.0));
        }
        return users;
    }
}
