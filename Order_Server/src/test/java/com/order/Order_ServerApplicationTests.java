package com.order;

import com.feign.entity.Order;
import com.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Order_ServerApplicationTests {
    @Autowired
    private OrderService orderService;

    @Test
    void contextLoads() {
        Order order = orderService.getById(5);
        System.out.println("order===>>>" + order);
    }
}
