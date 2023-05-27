package ru.example.java.spring.demo.security.authentication;

import reactor.core.publisher.Mono;

public interface JwtVerificationHandler {

    Mono<Verification> verification(String token);

}
