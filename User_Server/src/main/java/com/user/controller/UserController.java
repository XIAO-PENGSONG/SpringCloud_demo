package com.user.controller;

import com.user.entity.User;
import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RefreshScope
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

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

}
