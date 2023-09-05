package com.config.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.config.service.ConfigUserService;
import com.feign.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
@RequiredArgsConstructor
@RefreshScope // 支持属性自动刷新读取配置
public class ConfigController {
    
    private final ConfigUserService userService;
    
    // 从云端（Config服务器）获取配置：（分号:后面是默认值）
    @Value("${redis.url:null}")
    private String driver;
    @Value("${jdbc.username:null}")
    private String username;
    @Value("${jdbc.password:}")
    private String password;
    
    @RequestMapping("user/{id}")
    @SentinelResource("cloud-user-id")
    public User id(@PathVariable int id) {
        User user = userService.id(id);
        user.setUsername(user.getUsername() + "[" + username + "]");
        user.setPassword(driver + "-" + password);
        return user;
    }
}
