package com.gateway;

import com.gateway.filter.RequestFilter;
import com.gateway.filter.TimeFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsulGatewayApplication.class, args);
	}

	/**
	 * 代码配置Geteway路由,代码映射路由
	 * @param builder
	 * @return
	 */
	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(r -> r.path("/api-consumer/**").filters(f -> f.stripPrefix(1).filter(new TimeFilter()).filter(new RequestFilter())).uri("lb://CONSUL-CONSUMER"))
				.route(r -> r.path("/api-feign/**").filters(f -> f.stripPrefix(1)).uri("lb://CONSUL-FEIGN"))
				.build();
	}
}
