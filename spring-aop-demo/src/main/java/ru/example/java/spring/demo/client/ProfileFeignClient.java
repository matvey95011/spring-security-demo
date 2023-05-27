package ru.example.java.spring.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.example.java.spring.demo.api.ProfileApi;

@FeignClient(name = "profileFeignClient", url = "http://localhost:8081")
public interface ProfileFeignClient extends ProfileApi {
}
