package com.config.advice;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.feign.entity.User;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FallbackControllerAdvice {
    // 处理限流规则中的异常：
    @ExceptionHandler(BlockException.class)
    public User sentinel(BlockException e) {
        return new User(0, "其他熔断异常", e.toString(), 0.0);
    }

    @ExceptionHandler(FlowException.class)
    public User sentinel(FlowException e) {
        return new User(0, "限流异常", e.toString(), 0.0);
    }

    @ExceptionHandler(DegradeException.class)
    public User sentinel(DegradeException e) {
        return new User(0, "降级处理", e.toString(), 0.0);
    }
}
