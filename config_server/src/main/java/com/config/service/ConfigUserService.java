package com.config.service;

import com.feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
支持回滚（异常处理）
    url 表示绝对路径，可以不用name
    fallback 表示一个回滚的操作，如果请求遇到异常，就会使用该类的方法
        1. 必须实现这个@FeignClient接口
        2. 必须是一个Bean
    fallbackFactory 也是一个类，但是这个类可以有多种自定义方法
 */
// @FeignClient(name = "cloud-order", url = "http://localhost:8101/user")
@FeignClient(value = "orderserver", fallback = FallbackUserService.class)
public interface ConfigUserService {
    @RequestMapping("order/api/user/{id}")
    User id(@PathVariable("id") int id);
    
    @RequestMapping("order/api/user/all")
    List<User> all();
}
