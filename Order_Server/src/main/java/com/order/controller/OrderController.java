package com.order.controller;

import com.order.entity.Order;
import com.order.feign.UserFeign;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final UserFeign userFeign;

    @RequestMapping("{id}")
    public Order getOrder(@PathVariable("id") Integer id) {
        System.out.println("Order Id===>>" + id);
        Order order = orderService.getById(id);
        order.setUser(userFeign.getById(order.getUid()));
        return order;
    }
}
