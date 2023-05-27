package ru.example.java.spring.demo.security.authorization;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Principal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrincipalImpl implements Principal {

    private Long userId;
    private String name;

}
