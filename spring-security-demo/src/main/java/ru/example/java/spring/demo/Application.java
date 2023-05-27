package ru.example.java.spring.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.example.java.spring.demo.model.RegisterRequest;
import ru.example.java.spring.demo.service.AuthenticationService;

import static ru.example.java.spring.demo.entity.enums.Role.ADMIN;
import static ru.example.java.spring.demo.entity.enums.Role.MANAGER;
import static ru.example.java.spring.demo.entity.enums.Role.USER;

@Slf4j
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service
    ) {
        return args -> {
            var admin = RegisterRequest.builder()
                    .firstname("Admin")
                    .lastname("Admin")
                    .email("admin@gmail.com")
                    .password("password")
                    .role(ADMIN)
                    .build();
            System.out.println("Admin token: " + service.register(admin).getAccessToken());

            var manager = RegisterRequest.builder()
                    .firstname("Manager")
                    .lastname("Manager")
                    .email("manager@gmail.com")
                    .password("password")
                    .role(MANAGER)
                    .build();
            System.out.println("Manager token: " + service.register(manager).getAccessToken());

            var user = RegisterRequest.builder()
                    .firstname("User")
                    .lastname("User")
                    .email("user@gmail.com")
                    .password("password")
                    .role(USER)
                    .build();
            System.out.println("User token: " + service.register(user).getAccessToken());
        };
    }

}
