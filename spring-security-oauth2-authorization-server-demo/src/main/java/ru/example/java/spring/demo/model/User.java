package ru.example.java.spring.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public class User implements UserDetails {

    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private Set<GrantedAuthority> authorities;
    private UUID id;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
}