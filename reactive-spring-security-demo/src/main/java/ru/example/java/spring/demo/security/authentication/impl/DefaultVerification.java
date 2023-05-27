package ru.example.java.spring.demo.security.authentication.impl;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import ru.example.java.spring.demo.security.authentication.Verification;

@AllArgsConstructor
public class DefaultVerification implements Verification {

    private Claims claims;
    private String token;

    @Override
    public Claims getClaims() {
        return claims;
    }

    @Override
    public String getToken() {
        return token;
    }
}
