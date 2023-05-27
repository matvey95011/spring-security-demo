package ru.example.java.spring.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.example.java.spring.demo.entity.Status;
import ru.example.java.spring.demo.entity.UserRole;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

//    private Long id;
    private String username;
    private UserRole role;
    private String firstName;
    private String lastName;
    private Status status;
//    private LocalDateTime createdAt;
//    private LocalDateTime updatedAt;

}
