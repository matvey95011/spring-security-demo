package ru.example.java.spring.exception;

public class ResponseEntityException extends RuntimeException {
    public ResponseEntityException(String message) {
        super(message);
    }
}
