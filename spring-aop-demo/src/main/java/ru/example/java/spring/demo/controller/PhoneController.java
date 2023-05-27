package ru.example.java.spring.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.example.java.spring.demo.model.PhoneResponse;
import ru.example.java.spring.demo.service.PhoneService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/phones")
@RequiredArgsConstructor
public class PhoneController {

    private final PhoneService phoneService;

    @GetMapping
    public ResponseEntity<List<PhoneResponse>> getPhones() {
        return ResponseEntity.ok(phoneService.findAllPhone());
    }
}
