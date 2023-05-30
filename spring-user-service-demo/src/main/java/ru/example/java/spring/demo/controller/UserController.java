package ru.example.java.spring.demo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.example.java.spring.api.UserApi;
import ru.example.java.spring.demo.service.UserService;
import ru.example.java.spring.model.AuthenticationUserResponse;
import ru.example.java.spring.model.FilterRequest;
import ru.example.java.spring.model.PageUserResponse;
import ru.example.java.spring.model.UserRequest;
import ru.example.java.spring.model.UserResponse;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    @Override
    public ResponseEntity<UserResponse> addUser(String authorization, UserRequest userRequest) {
        return ResponseEntity.ok(userService.add(userRequest));
    }

    @Override
    public ResponseEntity<Void> deleteUser(String authorization, UUID id) {
        userService.delete(id);
        return null;
    }

    @Override
    public ResponseEntity<PageUserResponse> findAllUser(String authorization, FilterRequest filter) {
        return ResponseEntity.ok(userService.findAllUser(filter));
    }

    @Override
    public ResponseEntity<UserResponse> findUserById(String authorization, UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Override
    public ResponseEntity<AuthenticationUserResponse> findUserByUsername(String authorization, String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }
}
