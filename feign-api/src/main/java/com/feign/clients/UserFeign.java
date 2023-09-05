package com.feign.clients;

import com.feign.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "userserver")
public interface UserFeign {

    @RequestMapping("/user/{id}")
    User getById(@PathVariable("id") int id);

}
