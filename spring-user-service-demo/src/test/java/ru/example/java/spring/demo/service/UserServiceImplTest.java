package ru.example.java.spring.demo.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import ru.example.java.spring.demo.entity.User;
import ru.example.java.spring.demo.mapper.UserMapper;
import ru.example.java.spring.demo.repository.UserRepository;
import ru.example.java.spring.model.UserResponse;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class UserServiceImplTest {

    private final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private final User USER = convertJsonToObject("json/User.json", User.class);

    private UserService userService;

    @MockBean
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void beforeEach() {
        userService = new UserServiceImpl(userRepository, userMapper);
    }

    @Test
    void add() {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(USER));

        UserResponse byId = userService.findById(UUID.randomUUID());

        verify(userRepository, times(1))
                .findByUsername(any());


//        Assertions.assertThat(byId).hasFieldOrProperty()

    }

    @Test
    void delete() {
    }

    @Test
    void findAllUser() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByUsername() {
    }

    private User createUser() {
        User user = new User();



        return user;
    }

    private <T> T convertJsonToObject(String path, Class<T> tClass) {
        try {
            log.info("convertJsonToObject(), Path: {}, Class: {}", path, tClass);

            URL resource = Thread.currentThread().getContextClassLoader().getResource(path);

            return objectMapper.readValue(
                    new File(Objects.requireNonNull(resource).toURI()),
                    tClass
            );
        } catch (URISyntaxException | IOException e) {
            log.error("Fain to create Object");
            log.error(e.getMessage());
            return null;
        }
    }
}