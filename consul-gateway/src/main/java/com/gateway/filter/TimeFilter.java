package com.gateway.filter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Gateway局部过滤器,统计请求时间
 */
@Component
public class TimeFilter implements GatewayFilter, Ordered {

    private static final Log logger = LogFactory.getLog(TimeFilter.class);
    private static final String COUNT_Start_TIME = "countStartTime";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //pre
        exchange.getAttributes().put(COUNT_Start_TIME, System.currentTimeMillis());

        //post
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(COUNT_Start_TIME);
            Long endTime = System.currentTimeMillis() - startTime;
            if (startTime != null) {
                logger.info(exchange.getRequest().getURI().toString() + ":" + endTime + "ms");
            }
        }));
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
