package ru.example.java.spring.demo.security.authorization;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDetails {

    private Long userId;
    private String username;
    private String token;
    private Date issueAt;
    private Date expiresAt;

}
