package ru.example.java.spring.demo.service;

import ru.example.java.spring.demo.entity.User;

public interface UserService {

    User findByEmail(String email);

}
