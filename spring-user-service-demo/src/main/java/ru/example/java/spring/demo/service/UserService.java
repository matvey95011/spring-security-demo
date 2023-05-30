package ru.example.java.spring.demo.service;

import ru.example.java.spring.model.AuthenticationUserResponse;
import ru.example.java.spring.model.FilterRequest;
import ru.example.java.spring.model.PageUserResponse;
import ru.example.java.spring.model.UserRequest;
import ru.example.java.spring.model.UserResponse;

import java.util.UUID;

public interface UserService {
    UserResponse add(UserRequest request);

    void delete(UUID id);

    PageUserResponse findAllUser(FilterRequest filter);

    UserResponse findById(UUID id);

    AuthenticationUserResponse findByUsername(String username);
}
