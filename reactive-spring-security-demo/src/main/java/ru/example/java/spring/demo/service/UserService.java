package ru.example.java.spring.demo.service;

import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.entity.UserEntity;

public interface UserService {

    Mono<UserEntity> findByUsername(String username);

    Mono<UserEntity> findById(Long id);

    Mono<UserEntity> save(UserEntity user);
}
