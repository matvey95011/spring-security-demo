package ru.example.java.spring.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.example.java.spring.demo.entity.User;
import ru.example.java.spring.model.AuthenticationUserResponse;
import ru.example.java.spring.model.UserRequest;
import ru.example.java.spring.model.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    AuthenticationUserResponse toAuthUser(User user);

    @Mapping(target = "status", constant = "ACTIVE")
    @Mapping(target = "roles", expression = "java(Set.of(Role.USER))")
    @Mapping(target = "enabled", constant = "true")
    @Mapping(target = "createdAt", expression = "java(java.time.Instant.now())")
    User toEntity(UserRequest request);

    default User toEntity(User user, UserRequest request) {
        if (request == null) {
            return user;
        }

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMiddleName(request.getMiddleName());
        user.setEmail(request.getEmail());

        return user;
    }

}
