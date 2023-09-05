package com.sentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.feign.entity.User;
import com.sentinel.service.FeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
// @SentinelResource // 全局限制该控制器
public class UserController {
    
    private final RestTemplate restTemplate;
    private final FeignService feignService;
    
    // 设置监控的资源配置
    @SentinelResource("sentinel-user")
    @GetMapping("/sentinel/user/{id}")
    public User feignUser(@PathVariable int id) {
        User user = feignService.getUser(id);
        user.setPassword(user.getPassword() + "【来自Sentinel-A】");
        return user;
    }
    
    @SentinelResource("sentinel-test")
    @GetMapping("/sentinel/test/{id}")
    public User testSentinel(@PathVariable int id) {
        User user = feignService.getUser(id);
        user.setPassword(user.getPassword() + "【来自Sentinel-B】");
        if (Math.random() > 0.3){
            System.out.println(1 / 0);
        }
        return user;
    }
}
