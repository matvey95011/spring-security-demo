package ru.example.java.spring.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import ru.example.java.spring.demo.api.PhoneApi;

import java.net.URI;

@FeignClient(value = "phoneFeignClient", url = "${application.feign-clients.phone-service}")
public interface PhoneFeignClient extends PhoneApi {

    @GetMapping("/api/v1/delete")
    ResponseEntity<Void> deletePhone(URI url, Long id);

}
