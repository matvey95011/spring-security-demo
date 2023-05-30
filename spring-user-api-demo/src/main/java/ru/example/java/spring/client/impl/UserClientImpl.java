package ru.example.java.spring.client.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import ru.example.java.spring.client.UserClient;
import ru.example.java.spring.feign.UserFeignClient;
import ru.example.java.spring.model.AuthenticationUserResponse;
import ru.example.java.spring.model.FilterRequest;
import ru.example.java.spring.model.PageUserResponse;
import ru.example.java.spring.model.UserRequest;
import ru.example.java.spring.model.UserResponse;
import ru.example.java.spring.utils.ResponseUtils;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserClientImpl implements UserClient {

    private final UserFeignClient client;

    @Override
    public @NotNull UserResponse create(@NotNull String authorization, @NotNull UserRequest userRequest) {
        log.info("execute addUser, UserRequest: {}", userRequest);
        return ResponseUtils
                .build(client.addUser(authorization, userRequest))
                .status()
                .body();
    }

    @Override
    public void delete(@NotNull String authorization, @NotNull UUID id) {
        log.info("execute deleteUser, UUID: {}", id);
        ResponseUtils
                .build(client.deleteUser(authorization, id))
                .status();
    }

    @Override
    public @NotNull PageUserResponse findAll(@NotNull String authorization, @NotNull FilterRequest filter) {
        log.info("execute findAllUser, FilterRequest: {}", filter);
        return ResponseUtils
                .build(client.findAllUser(authorization, filter))
                .status()
                .body();
    }

    @Override
    public @NotNull UserResponse findById(@NotNull String authorization, @NotNull UUID id) {
        log.info("execute findByUserId, UUID: {}", id);
        return ResponseUtils
                .build(client.findUserById(authorization, id))
                .status()
                .body();
    }

    @Override
    public @NotNull AuthenticationUserResponse auth(@NotNull String authorization, @NotNull String username) {
        log.info("execute findUserByUsername, username: {}", username);
        return ResponseUtils
                .build(client.findUserByUsername(authorization, username))
                .status()
                .body();
    }
}
