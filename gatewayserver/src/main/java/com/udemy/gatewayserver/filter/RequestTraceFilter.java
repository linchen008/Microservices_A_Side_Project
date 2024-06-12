package com.udemy.gatewayserver.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 11/06/2024 23:46
 * @Description :
 */

@Component
@Order(1)
@Slf4j
public class RequestTraceFilter implements GlobalFilter {

    @Autowired
    private FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        if (isCorrelationIdPresent(requestHeaders)) {
            log.info("Correlation Id Present: {}", filterUtility.getCorrelationId(requestHeaders));

        } else {
            String correlationID = generateCorrelationId();
            exchange = filterUtility.setCorrelationId(exchange, correlationID);
            log.info("Correlation Id Set: {}", correlationID);
        }
        return chain.filter(exchange);
    }

    //helper fun 1:
    private boolean isCorrelationIdPresent(HttpHeaders requestHeaders) {
        if (filterUtility.getCorrelationId(requestHeaders) != null) {
            return true;
        } else {
            return false;
        }
    }

    //helper fun 2
    private String generateCorrelationId() {
        return java.util.UUID.randomUUID().toString();
    }
}
