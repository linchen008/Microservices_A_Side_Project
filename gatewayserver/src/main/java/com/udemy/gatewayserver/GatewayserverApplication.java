package com.udemy.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }
    //Method 1: config Gateway use "application.yml"() //todo: !!!!!!can not get time with this expression, use Method2!!!!!!!
    //Method 2: config Gateway use Java ConfigClass(active)
    @Bean
    public RouteLocator linsBankRouteConfig(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p->p
                        .path("/linsbank/accounts/**")
                        .filters(f->f
                                .rewritePath("/linsbank/accounts(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://ACCOUNTS"))
                .route(p->p
                        .path("/linsbank/loans/**")
                        .filters(f->f
                                .rewritePath("/linsbank/loans/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://LOANS"))
                .route(p->p
                        .path("/linsbank/cards/**")
                        .filters(f->f
                                .rewritePath("/linsbank/cards/(?<segment>.*)","/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://CARDS")).build();
    }
}