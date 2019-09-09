package com.consul.consumer;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableSwagger2Doc
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumerApplication.class, args);
    }

    /**
     * 这里不需要注解@LoadBalanced，为什么？
     * 因为负载均衡算法需要自己实现
     * @return
     */
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
