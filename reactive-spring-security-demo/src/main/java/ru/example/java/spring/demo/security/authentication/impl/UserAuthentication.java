package ru.example.java.spring.demo.security.authentication.impl;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.security.authentication.Verification;
import ru.example.java.spring.demo.security.authorization.PrincipalImpl;

import java.security.Principal;
import java.util.List;

public class UserAuthentication {

    public static Mono<Authentication> authenticate(Verification verification) {
        Claims claims = verification.getClaims();

        String username = claims.get("username", String.class);
        Long userId = claims.get("userId", Long.class);

        List<SimpleGrantedAuthority> authorityList =
                List.of(new SimpleGrantedAuthority(claims.get("role", String.class)));
        Principal principal = new PrincipalImpl(userId, username);

        return Mono.justOrEmpty(new UsernamePasswordAuthenticationToken(principal, null, authorityList));
    }

}
