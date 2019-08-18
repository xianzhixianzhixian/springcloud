package com.zookeeper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class ZookeeperServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZookeeperServerApplication.class, args);
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
