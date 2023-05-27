package ru.example.java.spring.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.example.java.spring.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

}
