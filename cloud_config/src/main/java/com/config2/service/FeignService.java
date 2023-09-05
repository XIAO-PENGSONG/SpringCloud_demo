package com.config2.service;

import com.feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

// 使用FeignClient去代理调用cloud-user服务的API
// @FeignClient类似于@Component
@FeignClient("userserver") // 服务名
public interface FeignService {
    // 请求路径必须和服务名一致，代理会去调用
    @RequestMapping("/user/{id}")
    // 注意：这里"id"不能省略
    User getUser(@PathVariable("id") int id);
}
