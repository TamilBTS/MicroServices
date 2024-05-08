package com.spring.gatewayserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @GetMapping("/contactSupport")
    public Mono<String> getContactInfo() {
        return Mono.just("An error occurred. Please try again after sometime");
    }
}
