package com.config2.controller;

import com.config2.service.FeignService;
import com.feign.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RefreshScope // 自动刷新远程的配置
public class ConfigController {
    
    private final FeignService feignService;
    
    // 从服务器中拉取配置
    @Value("${cloud.config.name:未定义}")
    private String name;
    @Value("${cloud.config.url:}")
    private String url;
    
    // 共用配置
    @Value("${jdbc.driver:未配置}")
    private String driver;
    @Value("${jdbc.pass:未配置}")
    private String pass;
    
    @GetMapping("/config/common/{id}")
    public User common(@PathVariable int id) {
        User user = feignService.getUser(id);
        user.setUsername(name);
        user.setPassword("驱动：" + driver + "，密码：" + pass);
        return user;
    }
    
    @GetMapping("/config/test/{id}")
    public User test(@PathVariable int id) {
        User user = feignService.getUser(id);
        user.setUsername(name);
        user.setPassword(user.getPassword() + "【" + url + "】");
        return user;
    }
}
