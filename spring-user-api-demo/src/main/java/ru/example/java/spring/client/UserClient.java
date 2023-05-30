package ru.example.java.spring.client;

import org.jetbrains.annotations.NotNull;
import ru.example.java.spring.model.AuthenticationUserResponse;
import ru.example.java.spring.model.FilterRequest;
import ru.example.java.spring.model.PageUserResponse;
import ru.example.java.spring.model.UserRequest;
import ru.example.java.spring.model.UserResponse;

import java.util.UUID;

public interface UserClient {

    /**
     * Создание пользователя
     *
     * @param userRequest  (optional)
     * @return CREATED (status code 201)
     */
    @NotNull UserResponse create(@NotNull String authorization, @NotNull UserRequest userRequest);


    /**
     * Удаление пользователя
     *
     * @param id  (required)
     */
    void delete(@NotNull String authorization, @NotNull UUID id);


    /**
     * Получение списка пользователей по фильтру
     *
     * @param filter  (optional)
     * @return PageUserResponse
     */
    @NotNull PageUserResponse findAll(@NotNull String authorization, @NotNull FilterRequest filter);


    /**
     * Получение пользователя по id
     *
     * @param id  (required)
     * @return UserResponse
     */
    @NotNull UserResponse findById(@NotNull String authorization, @NotNull UUID id);

    @NotNull AuthenticationUserResponse auth(@NotNull String authorization, @NotNull String username);

}
