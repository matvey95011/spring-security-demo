package ru.example.java.spring.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.example.java.spring.demo.entity.UserEntity;
import ru.example.java.spring.demo.entity.UserRole;
import ru.example.java.spring.demo.repository.UserRepository;

import java.time.LocalDateTime;

import static ru.example.java.spring.demo.entity.Status.CREATED;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Mono<UserEntity> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Mono<UserEntity> findById(Long id) {
        return null;
    }

    @Override
    public Mono<UserEntity> save(UserEntity user) {
        UserEntity save = user.toBuilder()
                .password(passwordEncoder.encode(user.getPassword()))
                .role(UserRole.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(CREATED)
                .build();

        return userRepository.save(save);
    }
}
