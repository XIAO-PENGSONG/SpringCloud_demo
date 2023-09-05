package com.config2.service;

import com.feign.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public User getById(int id){
        User user = new User();
        user.setId(id);
        user.setUsername("SpringCloud用户00" + id);
        user.setPassword("123456");
        user.setMoney(100.0);
        return user;
    }
}
