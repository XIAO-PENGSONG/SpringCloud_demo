package com.order.feign;

import com.order.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "cloud-user")
public interface UserFeign {

    @RequestMapping("/user/{id}")
    User getById(@PathVariable("id") int id);

}
