package ru.example.java.spring.demo.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.example.java.spring.demo.model.AuthenticationRequest;
import ru.example.java.spring.demo.model.AuthenticationResponse;
import ru.example.java.spring.demo.model.RegisterRequest;

import java.io.IOException;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
