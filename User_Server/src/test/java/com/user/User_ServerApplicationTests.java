package com.user;

import com.user.entity.User;
import com.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class User_ServerApplicationTests {
    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        User user = userService.getById(4);
        System.out.println("user==>>>" + user);
    }
}
