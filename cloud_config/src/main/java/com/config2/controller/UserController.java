package com.config2.controller;

import com.config2.service.FeignService;
import com.feign.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class UserController {
    
    // 用于对其他服务发起请求获取响应
    private final RestTemplate restTemplate;
    // 导入@FeignClient注入的接口依赖
    private final FeignService feignService;
    
    // 这里是订单服务，使用RestTemplate获取用户服务的信息
    @GetMapping("/order/user/{id}")
    public User user(@PathVariable int id) {
        // 请求链接中使用服务名，而不是具体的IP地址和具体的Port端口
        return restTemplate.getForObject("http://userserver/user/" + id, User.class);
    }
    
    @GetMapping("/feign/user/{id}")
    public User feignUser(@PathVariable int id) {
        User user = feignService.getUser(id);
        user.setPassword(user.getPassword() + "【来自OpenFeign】");
        return user;
    }
}
