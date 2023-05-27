package ru.example.java.spring.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.example.java.spring.demo.aop.Authorization;
import ru.example.java.spring.demo.client.PhoneFeignClient;
import ru.example.java.spring.demo.model.PhoneResponse;
import ru.example.java.spring.demo.utils.ResponseUtils;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneFeignClient client;

    @Override
    @Authorization
    public List<PhoneResponse> findAllPhone() {
        return ResponseUtils
                .build(client.findAllPhone())
                .status()
                .body();
    }
}
