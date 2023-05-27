package ru.example.java.spring.demo.service;

import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.security.authorization.TokenDetails;

public interface SecurityService {

    Mono<TokenDetails> authentication(String username, String password);

}
