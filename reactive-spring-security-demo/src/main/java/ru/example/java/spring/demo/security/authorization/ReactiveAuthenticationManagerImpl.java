package ru.example.java.spring.demo.security.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.service.UserService;

@Component
@RequiredArgsConstructor
public class ReactiveAuthenticationManagerImpl implements ReactiveAuthenticationManager {

    private final UserService userService;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        PrincipalImpl principal = (PrincipalImpl) authentication.getPrincipal();
        return userService.findByUsername(principal.getName())
                .switchIfEmpty(Mono.error(new RuntimeException("")))
                .map(user -> authentication);
    }
}
