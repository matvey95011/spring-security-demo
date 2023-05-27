package ru.example.java.spring.demo.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.entity.UserEntity;

import java.util.List;

public interface UserRepository extends R2dbcRepository<UserEntity, Long> {

    Mono<UserEntity> findByUsername(String username);

}
