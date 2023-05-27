package ru.example.java.spring.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.mapper.AuthMapper;
import ru.example.java.spring.demo.mapper.UserMapper;
import ru.example.java.spring.demo.model.AuthRequest;
import ru.example.java.spring.demo.model.AuthResponse;
import ru.example.java.spring.demo.model.UserRequest;
import ru.example.java.spring.demo.model.UserResponse;
import ru.example.java.spring.demo.security.authorization.PrincipalImpl;
import ru.example.java.spring.demo.service.SecurityService;
import ru.example.java.spring.demo.service.UserService;

import java.security.Principal;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final SecurityService securityService;
    private final UserService userService;
    private final AuthMapper authMapper;
    private final UserMapper userMapper;

    @Override
    public Mono<AuthResponse> login(AuthRequest request) {
        return securityService.authentication(request.getUsername(), request.getPassword())
                .map(authMapper::toAuthResponse);
    }

    @Override
    public Mono<UserResponse> registration(UserRequest request) {
        return userService.save(userMapper.toEntity(request))
                .map(userMapper::toUserResponse);
    }

    @Override
    public Mono<UserResponse> getInfoCurrentUser(Authentication authentication) {
        PrincipalImpl principal = (PrincipalImpl) authentication.getPrincipal();
        return userService.findByUsername(principal.getName())
                .map(userMapper::toUserResponse);
    }

    @Override
    public Mono<UserResponse> getUserById(Long id) {
        return null;
    }

    @Override
    public Flux<UserResponse> getAll() {
        return null;
    }
}
