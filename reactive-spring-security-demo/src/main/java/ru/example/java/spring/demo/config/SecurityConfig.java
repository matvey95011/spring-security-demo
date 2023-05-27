package ru.example.java.spring.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfig {

    private final ServerAuthenticationEntryPoint unauthorized = (exchange, exception) ->
            Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(UNAUTHORIZED));
    private final ServerAccessDeniedHandler forbidden = (exchange, denied) ->
            Mono.fromRunnable(() -> exchange.getResponse().setStatusCode(FORBIDDEN));

    @Bean
    public SecurityWebFilterChain securityFilterChain(
            ServerHttpSecurity http,
            AuthenticationWebFilter filter,
            SecurityProperties properties
    ) {
        return http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers(properties.getPublicEndpoints())
                .permitAll()
                .anyExchange()
                .authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorized)
                .accessDeniedHandler(forbidden)
                .and()
                .addFilterAt(filter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @Bean
    public AuthenticationWebFilter bearerAuthorizationWebFilter(
            ReactiveAuthenticationManager authenticationManager,
            ServerAuthenticationConverter tokenConverter
    ) {
        var filter = new AuthenticationWebFilter(authenticationManager);
        filter.setServerAuthenticationConverter(tokenConverter);
        filter.setRequiresAuthenticationMatcher(ServerWebExchangeMatchers.pathMatchers("/**"));
        return filter;
    }


}
