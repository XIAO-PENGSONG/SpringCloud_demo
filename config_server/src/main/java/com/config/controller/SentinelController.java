package com.config.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.feign.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/sentinel")
@RequiredArgsConstructor
public class SentinelController {
    
    public User fallback() {
        return new User(0, "[fallback]异常处理", "000000", 0.0);
    }
    
    @SentinelResource(value = "user-id", fallback = "fallback")
    @RequestMapping("user/{id}")
    public User id(@PathVariable int id) {
        if (Math.random() > 0.5) {
            // System.out.println(1/0);
        }
        if (Math.random() > 0.5) {
            // System.out.println("".charAt(1));
        }
        return new User(id, "user" + id, "pass" + id, (double) id);
    }
    
    @SentinelResource(value = "user-all")
    @RequestMapping("users")
    public List<User> users() throws IOException {
        if (Math.random() > 0.5) {
            throw new NullPointerException("空空如也");
        }
        if (Math.random() > 0.5) {
            throw new IOException("IO错误");
        }
        return Arrays.asList(new User(1111, "a", "A", 1.1));
    }
}
