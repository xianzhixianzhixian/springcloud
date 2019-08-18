package com.zookeeper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/zookeeper")
public class ZookeeperController {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取已注册的服务
     * @return
     */
    @RequestMapping(value = "/getServices")
    public List<String> getServices() {
        List<String> services = discoveryClient.getServices();
        return services;
    }

    public List<String> getServiceUriList(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances(name);
        List<String> services = new ArrayList<>();
        for (ServiceInstance instance : list) {
            if (instance != null) {
                services.add(instance.getUri().toString());
            }
        }
        return services;
   }

    /**
     * 调用注册中心注册的服务
     * 有两种调用方法：1、采用服务别名，2、直接调用，使用别名去注册中心获取对应服务的调用地址
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/consumer")
    public String consumer() throws Exception {
        String serviceUrl = getServiceUri("zookeeper-client") + "/user/get";
        String result = (String) restTemplate.getForObject(serviceUrl, String.class);
        return result;
    }

    /**
     * 获取注册中心服务链接
     * @param name
     * @return
     */
    protected String getServiceUri(String name) {
        List<ServiceInstance> list = discoveryClient.getInstances(name);
        if (list != null && !list.isEmpty()) {
            //这里需要实现负载均衡算法，取余算法，权重值算法
            return list.get(0).getUri().toString();
        }
        return null;
    }
}
