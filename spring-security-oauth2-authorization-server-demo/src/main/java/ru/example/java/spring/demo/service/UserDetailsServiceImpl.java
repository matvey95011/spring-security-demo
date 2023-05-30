package ru.example.java.spring.demo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import ru.example.java.spring.client.UserClient;
import ru.example.java.spring.demo.model.TokenInfo;
import ru.example.java.spring.demo.model.User;
import ru.example.java.spring.model.AuthenticationUserResponse;

import java.time.Clock;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private String token;
    private final JwtDecoder jwtDecoder;
    private final UserClient client;

    private final Clock clock;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String token = getToken();
        AuthenticationUserResponse customer = client.auth(token, username);
        return User.builder()
                .username(customer.getUsername())
                .password(customer.getPassword())
                .authorities(customer.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toSet()))
                .id(customer.getId())
                .accountNonExpired(customer.isAccountNonExpired())
                .accountNonLocked(customer.isAccountNonLocked())
                .credentialsNonExpired(customer.isCredentialsNonExpired())
                .enabled(customer.isEnabled())
                .email(customer.getEmail())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }

    private String getToken() {
        if (token == null || jwtDecoder.decode(token).getExpiresAt().isBefore(clock.instant().minusSeconds(60 * 5))) {
//            var response = webClient.post()
//                    .uri("http://localhost:8081/oauth2/token?client_id=default_auth&client_secret=secret_auth_code&grant_type=client_credentials")
//                    .retrieve()
//                    .bodyToMono(TokenInfo.class)
//                    .block();
//            if (response == null) {
//                throw new BadCredentialsException("Token is null");
//            }
//            token = response.getAccessToken();
        }
        return token;

    }

}
