package ru.example.java.spring.demo.service;

import org.springframework.stereotype.Service;
import ru.example.java.spring.demo.aop.Authorization;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    @Authorization
    public void authorization(String login, String password) {
        //todo:
    }
}
