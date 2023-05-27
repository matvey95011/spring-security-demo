package ru.example.java.spring.demo.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.config.SecurityProperties;
import ru.example.java.spring.demo.entity.UserEntity;
import ru.example.java.spring.demo.security.authorization.TokenDetails;

import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityProperties securityProperties;

    @Override
    public Mono<TokenDetails> authentication(String username, String password) {
        return userService.findByUsername(username)
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                .flatMap(user -> passwordEncoder.matches(password, user.getPassword()) ?
                        Mono.just(createToken(user)) :
                        Mono.error(new RuntimeException("Password invalidate")));
    }

    private TokenDetails createToken(UserEntity user) {
        Date currentDate = new Date();
        Date expirationDate = new Date(currentDate.getTime() + securityProperties.getExpiration());
        return TokenDetails.builder()
                .username(user.getUsername())
                .userId(user.getId())
                .token(getToken(user, currentDate, expirationDate))
                .expiresAt(expirationDate)
                .issueAt(currentDate)
                .build();
    }

    private String getToken(UserEntity user, Date currentDate, Date expirationDate) {
        return Jwts.builder()
                .addClaims(getClaims(user))
                .setIssuer(securityProperties.getIssuer())
                .setSubject(user.getId().toString())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(currentDate)
                .setExpiration(expirationDate)
                .signWith(
                        SignatureAlgorithm.HS512,
                        Base64.getEncoder().encodeToString(securityProperties.getSecret().getBytes())
                )
                .compact();
    }

    private Map<String, Object> getClaims(UserEntity user) {
        return Map.of(
                "username", user.getUsername(),
                "userId", user.getId(),
                "role", user.getRole()
        );
    }

}
