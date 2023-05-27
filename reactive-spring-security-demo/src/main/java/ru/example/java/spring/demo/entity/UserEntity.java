package ru.example.java.spring.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@Table("users")
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@EqualsAndHashCode(of = {"id", "username"})
public class UserEntity {

    @Id
    private Long id;
    private String username;
    @ToString.Exclude
    private String password;
    private UserRole role;
    private String firstName;
    private String lastName;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
