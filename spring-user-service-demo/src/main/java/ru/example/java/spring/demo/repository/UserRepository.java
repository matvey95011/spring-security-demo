package ru.example.java.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.java.spring.demo.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);


}
