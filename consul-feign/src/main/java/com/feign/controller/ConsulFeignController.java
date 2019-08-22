package com.feign.controller;

import com.feign.util.MemberFeign;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/feign")
public class ConsulFeignController {

    @Autowired
    private MemberFeign memberFeign;

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping(value = "/getServices")
    public String getServices() {
        return memberFeign.getServices();
    }

    private String fallback() {
        return "服务器访问人数过多，请稍后再试";
    }
}
