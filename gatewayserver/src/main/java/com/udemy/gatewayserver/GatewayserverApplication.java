package com.udemy.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    //Method 1: config Gateway use "application.yml"() //todo: !!!!!!can not get time with this expression, use Method2!!!!!!!
    //Method 2: config Gateway use Java ConfigClass(active)
    @Bean
    public RouteLocator linsBankRouteConfig(RouteLocatorBuilder builder, RedisRateLimiter redisRateLimiter) {
        return builder.routes()
                .route(p -> p
                        .path("/linsbank/accounts/**")
                        .filters(f -> f
                                //TODO: circuitbreaker in Gateway
                                .circuitBreaker(config -> config.setName("accountsCircuitBreaker")
                                        .setFallbackUri("forward:/contactSupport"))

                                .rewritePath("/linsbank/accounts(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))

                        .uri("lb://ACCOUNTS"))

                .route(p -> p
                        .path("/linsbank/loans/**")
                        .filters(f -> f
                                //TODO: retry Method in Gateway
                                .retry(retryConfig -> retryConfig.setRetries(3)
                                        .setMethods(HttpMethod.GET)
                                        //.setBackoff(Duration.ofMinutes(100), Duration.ofMillis(1000), 2, true)
                                )

                                .rewritePath("/linsbank/loans/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://LOANS"))

                .route(p -> p
                        .path("/linsbank/cards/**")
                        .filters(f -> f
                                //TODO: rateLimit
                                .requestRateLimiter(config -> config.setRateLimiter(redisRateLimiter())
                                        .setKeyResolver(userKeyResolver()))

                                .rewritePath("/linsbank/cards/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://CARDS"))

                .build();
    }

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1, 1, 1);
    }

    @Bean
    KeyResolver userKeyResolver() {
        return exchange -> Mono.justOrEmpty(exchange.getRequest().getHeaders().getFirst("user"))
                .defaultIfEmpty("anonymous");
    }

    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory
                .configureDefault(id -> new Resilience4JConfigBuilder(id)
                        .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                        .timeLimiterConfig(TimeLimiterConfig
                                .custom()
                                .timeoutDuration(Duration.ofSeconds(4))
                                .build())
                        .build()
                );
    }
}