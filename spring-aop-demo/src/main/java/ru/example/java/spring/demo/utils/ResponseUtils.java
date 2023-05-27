package ru.example.java.spring.demo.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ResponseUtils<T> {
    private final ResponseEntity<T> response;

    public static <T> ResponseUtils<T> build(ResponseEntity<T> response) {
        return new ResponseUtils<>(
                Optional.ofNullable(response)
                        .orElseThrow(() -> new RuntimeException("response == null"))
        );
    }

    public ResponseUtils<T> status() {
        if (response.getStatusCode().isError()) {
            log.error("Response: STATUS-CODE={}", response.getStatusCode());
            throw new RuntimeException(response.getStatusCode().toString());
        }
        log.info("Response: STATUS-CODE={}", response.getStatusCode());
        return this;
    }

    public T body() {
        log.info("Response: BODY={}", response.getBody());
        return Optional.ofNullable(response.getBody())
                .orElseThrow(() -> new RuntimeException(response.getStatusCode().toString()));
    }

}
