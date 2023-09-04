package com.user.controller;

import com.user.entity.User;
import com.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
//@RefreshScope
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final RestTemplate restTemplate;


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

    @RequestMapping("rest/{id}")
    public User id2(@PathVariable int id) {
        // 负载均衡的请求方式【无需写IP和端口】：http://cloud-user/user/{id}
        User user = restTemplate.getForObject("http://cloud-user/user1/" + id,
                User.class);
        if (user != null) {
            user.setPassword(user.getPassword() + "【" + port + "】");
        }
        return user;
    }

}
