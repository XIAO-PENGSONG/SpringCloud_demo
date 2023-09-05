package com.sentinel.controller;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({FlowException.class})
    public String handleFlowException() {
        return "FlowException";
    }
    
    @ExceptionHandler({BlockException.class})
    public String handleBlockException() {
        return "BlockException";
    }
}
