package ru.example.java.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.example.java.spring.client.UserClient;
import ru.example.java.spring.client.impl.UserClientImpl;
import ru.example.java.spring.feign.UserFeignClient;

import java.time.Clock;

@Configuration
public class Config {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

    @Bean
    public UserClient userClient(UserFeignClient client) {
        return new UserClientImpl(client);
    }

}
