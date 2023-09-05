package com.sentinel.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class CloudConfig {
    // 负载均衡，并且认识SpringCloud微服务
    @Bean @LoadBalanced
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofSeconds(10)) // 链接超时
                // .setReadTimeout(Duration.ofSeconds(10)) // IO读取超时
                .build();
    }
    
    @PostConstruct
    public void flowRules() {
        List<FlowRule> rules = new ArrayList<>(); // 定义一套规则
        FlowRule rule = new FlowRule(); // 定义一个规则
        rule.setResource("sentinel-user"); // 设置资源
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); // 设置类型
        rule.setCount(2); // 设置限制：QPS=2，每秒2次以内
        rule.setLimitApp("default"); // 设置来源，default默认不论任何来源
        // rule.setStrategy() // 策略
        rules.add(rule); // 添加
        FlowRuleManager.loadRules(rules); // 添加所有规则
    }
}
