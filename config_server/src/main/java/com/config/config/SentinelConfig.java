package com.config.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import javax.servlet.http.HttpFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SentinelConfig {
    @Bean
    public FilterRegistrationBean sentinelFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new HttpFilter() {
            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                chain.doFilter(request, response);
            }
        });
        registration.addUrlPatterns("/*");
        registration.setName("sentinelFilter");
        registration.setOrder(1);

        return registration;
    }
    
    // 在启动后运行配置
    @PostConstruct
    // 配置Sentinel熔断机制的规则
    public void rules() {
        // 创建一些流量规则
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule(); // 创建一个限流规则
        rule.setResource("user-id"); // 设置资源名字
        rule.setCount(2); // 设置限流大小
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS); // 设置限流的类型：OPS/THREAD/EXCEPTION...
        rule.setLimitApp("default"); // 是否是集群，默认
        rules.add(rule); // 添加限流规则FlowRule rule = new FlowRule(); // 创建一个限流规则
        
        // 异常个数规则
        rule = new FlowRule();
        rule.setResource("user-all");
        rule.setCount(2);
        rule.setMaxQueueingTimeMs(3000);
        rule.setWarmUpPeriodSec(1000);
        rule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
        rule.setLimitApp("default");
        rules.add(rule);
        
        FlowRuleManager.loadRules(rules);
    }

}
