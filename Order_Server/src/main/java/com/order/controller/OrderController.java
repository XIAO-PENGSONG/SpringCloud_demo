package com.order.controller;

import com.feign.clients.UserFeign;
import com.feign.entity.Order;
import com.feign.entity.User;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final UserFeign userFeign;
    private final RestTemplate restTemplate;

    @Value("${server.port}")
    private int port;


    @RequestMapping("{id}")
    public Order getOrder(@PathVariable("id") Integer id,
                          @RequestHeader(value = "Truth", required = false)
                                  String Truth) {
        System.out.println("Order Id===>>" + id);
        System.out.println("Truth ===>>" + Truth);
        Order order = orderService.getById(id);
        order.setUser(userFeign.getById(order.getUid()));
        return order;
    }

    @RequestMapping("2/{id}")
    public Order getOrder1(@PathVariable("id") Integer id){
        System.out.println("Order Id===>>" + id);
        return orderService.getById(id);
    }


    @RequestMapping("user/old/{id}")
    public User id1(@PathVariable int id) {
        // 订单服务依赖于用户服务，这里需要在服务器中请求用户服务，获取用户数据
        // 这里路径和端口都是写死的，无法实现动态负载均衡
        // 期望的请求方式：http://cloud-user/user/{id}
        User user = restTemplate.getForObject("http://localhost:8101/user/" + id, User.class);
        if (user != null) {
            user.setPassword(user.getPassword() + "【" + port + "】");
        }
        return user;
    }

    private static final String USER_API_NAME = "cloud-user"; // cloud-produce/cloud-order/...

    @RequestMapping("user/{id}")
    public User id2(@PathVariable int id) {
        // 负载均衡的请求方式【无需写IP和端口】：http://cloud-user/user/{id}
        User user = restTemplate.getForObject("http://" + USER_API_NAME + "/user/" + id,
                User.class);
        if (user != null) {
            user.setPassword(user.getPassword() + "【" + port + "】");
        }
        return user;
    }


    @RequestMapping("api/user/{id}")
    public User id3(@PathVariable int id) {
        // 更进一步：只要告诉OpenFeign：服务名cloud-xx，哪些请求映射/user/xx，参数和返回值
        // 自动从OpenFeign中获取请求结果
        return userFeign.id(id);
    }
    @RequestMapping("api/user/all")
    public List<User> all() {
        return userFeign.all();
    }
}
