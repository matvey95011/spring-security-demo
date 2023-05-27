package ru.example.java.spring.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.model.AuthRequest;
import ru.example.java.spring.demo.model.AuthResponse;
import ru.example.java.spring.demo.model.UserRequest;
import ru.example.java.spring.demo.model.UserResponse;

@RequestMapping("/api/v1")
public interface UserController {

    @PostMapping("/login")
    Mono<AuthResponse> login(@RequestBody AuthRequest request);

    @PostMapping("/registration")
    Mono<UserResponse> registration(@RequestBody UserRequest request);

    @GetMapping
    Mono<UserResponse> getInfoCurrentUser(Authentication authentication);

    @GetMapping("/users/{id}")
    Mono<UserResponse> getUserById(@RequestParam("id") Long id);

    @GetMapping("/users")
    Flux<UserResponse> getAll();
}
