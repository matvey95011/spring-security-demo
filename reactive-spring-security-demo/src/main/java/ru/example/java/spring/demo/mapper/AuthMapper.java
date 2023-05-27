package ru.example.java.spring.demo.mapper;

import org.mapstruct.Mapper;
import ru.example.java.spring.demo.model.AuthResponse;
import ru.example.java.spring.demo.security.authorization.TokenDetails;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    AuthResponse toAuthResponse(TokenDetails tokenDetails);

}
