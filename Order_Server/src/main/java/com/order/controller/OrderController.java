package com.order.controller;

import com.feign.clients.UserFeign;
import com.order.entity.Order;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final UserFeign userFeign;

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
}
