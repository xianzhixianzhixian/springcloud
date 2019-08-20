package com.eureka.eurekaconsumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/getServices")
    public List<String> getServices() {
        return discoveryClient.getServices();
    }

    @RequestMapping(value = "/getServiceInstances")
    public List<ServiceInstance> getServiceInstances(String serviceId) {
        //Eureka获取的注册中心已注册的实例时获得的uri是spring.application.name的列表,所以在调用接口时Ribbon可以自动实现客户端负载均衡
        //uri类似http://xianzhixianzhixian:8083
        return discoveryClient.getInstances(serviceId);
    }

    @RequestMapping(value = "/get")
    public String getOrder() {
        //调用eureka-client中的用户服务,通过注册中心去调用Ribbon
        //注意这里使用的是application-name
        String restfulUrl = "http://eureka-client/user/get";
        String result = restTemplate.getForObject(restfulUrl, String.class);
        return "OrderController " + result;
    }
}
