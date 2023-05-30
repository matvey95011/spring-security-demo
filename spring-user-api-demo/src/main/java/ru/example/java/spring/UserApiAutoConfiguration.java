package ru.example.java.spring;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.example.java.spring.client.UserClient;
import ru.example.java.spring.client.impl.UserClientImpl;
import ru.example.java.spring.feign.UserFeignClient;

//@EnableAutoConfiguration
@Configuration
//@ComponentScan(basePackages = "ru.example.java.spring.client")
@EnableFeignClients(clients = {UserFeignClient.class})
public class UserApiAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public UserClient userClient(UserFeignClient client) {
        return new UserClientImpl(client);
    }

}
