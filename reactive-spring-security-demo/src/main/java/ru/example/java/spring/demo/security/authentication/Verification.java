package ru.example.java.spring.demo.security.authentication;

import io.jsonwebtoken.Claims;

public interface Verification {

    Claims getClaims();
    String getToken();

}
