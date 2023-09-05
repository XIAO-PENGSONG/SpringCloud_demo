package com.config.service;

import com.feign.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FallbackUserService implements ConfigUserService{
    @Override
    public User id(int id) {
        return new User(0, "空用户", "不存在", 0.0);
    }
    
    @Override
    public List<User> all() {
        return null;
    }
}
