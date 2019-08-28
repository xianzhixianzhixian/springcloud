package com.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Gateway局部过滤器,过滤请求
 */
@Component
public class RequestFilter implements GatewayFilter, Ordered {

    private static final Log logger = LogFactory.getLog(TimeFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            ServerHttpRequest request = exchange.getRequest();
            String path = request.getPath().toString();
            String addr = request.getRemoteAddress().getAddress().toString();
            MultiValueMap<String, String> queryParams = request.getQueryParams();
            logger.info("PATH:" + path);
            logger.info("ADDR:" + addr);
            logger.info("PARAM:" + queryParams);
        }));
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
