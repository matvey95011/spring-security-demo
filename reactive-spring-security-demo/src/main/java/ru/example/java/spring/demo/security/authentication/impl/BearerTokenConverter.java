package ru.example.java.spring.demo.security.authentication.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.security.authentication.JwtVerificationHandler;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class BearerTokenConverter implements ServerAuthenticationConverter {

    private final JwtVerificationHandler jwtHandler;

    private final static String PREFIX = "Bearer ";
    private final static Function<String, Mono<String>> getToken =
            token -> Mono.justOrEmpty(token.substring(PREFIX.length()));

    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange) {
        return getAuthToken(exchange)
                .flatMap(getToken)
                .flatMap(jwtHandler::verification)
                .flatMap(UserAuthentication::authenticate);
    }

    private Mono<String> getAuthToken(ServerWebExchange exchange) {
        return Mono.justOrEmpty(
                exchange.getRequest()
                        .getHeaders()
                        .getFirst(HttpHeaders.AUTHORIZATION));
    }
}
