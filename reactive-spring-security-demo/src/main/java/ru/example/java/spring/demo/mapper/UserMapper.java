package ru.example.java.spring.demo.mapper;

import org.mapstruct.Mapper;
import ru.example.java.spring.demo.entity.UserEntity;
import ru.example.java.spring.demo.model.UserRequest;
import ru.example.java.spring.demo.model.UserResponse;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(UserEntity user);

    UserEntity toEntity(UserRequest user);

}
