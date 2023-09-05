package com.feign.clients;

import com.feign.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "orderserver")
public interface OrderFeign {

    @RequestMapping("order/2/{id}")
    Order getOrder1(@PathVariable("id") Integer id);
}
