package com.udemy.gatewayserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author : Tommy
 * @version : 1.0
 * @createTime : 13/06/2024 19:14
 * @Description :
 */
@RestController
public class FallBackController {

    @RequestMapping("/contactSupport")
    public Mono<String> contactSupport() {
        return Mono.just("An error occurred. Please try after some time or contact support team!!!");
    }
}
