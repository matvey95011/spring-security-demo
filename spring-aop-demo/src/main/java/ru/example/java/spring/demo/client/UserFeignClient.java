package ru.example.java.spring.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import ru.example.java.spring.demo.api.UserApi;

@FeignClient(name = "userFeignClient", url = "http://localhost:8081")
public interface UserFeignClient extends UserApi {
}
