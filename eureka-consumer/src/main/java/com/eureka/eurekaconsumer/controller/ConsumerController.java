package com.eureka.eurekaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/order")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/get")
    public String getOrder() {
        //调用eureka-client中的用户服务,通过注册中心去调用Ribbon
        //注意这里使用的是application-name
        String restfulUrl = "http://eureka-client/user/get";
        String result = restTemplate.getForObject(restfulUrl, String.class);
        return "OrderController " + result;
    }
}
