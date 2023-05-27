package ru.example.java.spring.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security-variable")
public class SecurityProperties {

    private String secret;
    private Long expiration;
    private String issuer;
    private String[] publicEndpoints;

}
