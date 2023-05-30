package ru.example.java.spring.feign;

import org.springframework.cloud.openfeign.FeignClient;
import ru.example.java.spring.api.UserApi;

@FeignClient(name = "userFeignClient", url = "${feign-clients.main-service}")
public interface UserFeignClient extends UserApi {
}
