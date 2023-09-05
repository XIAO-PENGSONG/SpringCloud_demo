package com.user.controller;

import com.feign.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user2")
//@RequiredArgsConstructor
@RefreshScope
public class ConfigController {
//    private final PatternProperties properties;

    @Value("${server.port}")
    private int port;

    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${pattern.envSharedValue}")
    private String envSharedValue;

    @GetMapping("now")
    public String now() {
//        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat()));
        return "当前环境==>>>" + envSharedValue + "  ;driver==>>" + driver + "   url===>>>" + url +
                "  ;username==>>" + username + " ;password==>>" + password;
    }

    @RequestMapping("{id}")
    public User id(@PathVariable int id) {
        if (Math.random() > 0.5) {
            // throw new RuntimeException("error");
            System.out.println(1/0);
        }
        return new User(id, "来自端口：【" + port + "】", envSharedValue, 100.0);
    }
}
