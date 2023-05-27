package ru.example.java.spring.demo.security.authentication.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.config.SecurityProperties;
import ru.example.java.spring.demo.security.authentication.JwtVerificationHandler;
import ru.example.java.spring.demo.security.authentication.Verification;
import ru.example.java.spring.demo.security.authentication.impl.DefaultVerification;

import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtVerificationHandlerImpl implements JwtVerificationHandler {

    private final String secret;

    public JwtVerificationHandlerImpl(SecurityProperties properties) {
        this.secret = properties.getSecret();
    }

    @Override
    public Mono<Verification> verification(String token) {
        return Mono.just(verify(token));
    }

    private Verification verify(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date()) ?
                tokenExpired() :
                new DefaultVerification(claims, token);
    }

    private Verification tokenExpired() {
        throw new RuntimeException("Token expired");
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(Base64.getEncoder().encodeToString(secret.getBytes()))
                .parseClaimsJws(token)
                .getBody();
    }
}
