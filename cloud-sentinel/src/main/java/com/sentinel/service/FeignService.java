package com.sentinel.service;

import com.feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("userserver")
public interface FeignService {
    
    // 标记为Sentinel监控的资源，必须设置fallback
    // @SentinelResource(value = "sentinel-user", fallback = "", blockHandler = "")
    @RequestMapping("/user/{id}")
    User getUser(@PathVariable("id") int id);
}
