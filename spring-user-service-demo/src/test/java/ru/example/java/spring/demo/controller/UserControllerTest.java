package ru.example.java.spring.demo.controller;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import ru.example.java.spring.demo.config.AbstractTest;
import ru.example.java.spring.demo.config.TestBeanContext;
import ru.example.java.spring.demo.repository.UserRepository;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest extends AbstractTest {

    private final String URL = "/api/v1/user";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    void beforeAll() {
        config(
                TestBeanContext.builder()
                        .userRepository(userRepository)
                        .build()
        );

        createUser("test username");

    }

    @AfterAll
    void afterAll() {
        clearDb();
    }

    @Test
    @SneakyThrows
    void findAllUser() {
        mockMvc.perform(get(URL).header(HttpHeaders.AUTHORIZATION, "token"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.totalElements").value(30))
                .andExpect(jsonPath("$.content.*", hasSize(10)))

                .andExpect(jsonPath("$.content.[0].id").value(30))
                .andExpect(jsonPath("$.content.[0].externalId").value("externalId 30"))
                .andExpect(jsonPath("$.content.[0].startDate").value(LocalDate.now().minusDays(30).toString()))
                .andExpect(jsonPath("$.content.[0].endDate").value(LocalDate.now().plusDays(30).toString()))

                .andExpect(jsonPath("$.content.[0].account.id").value(30))
                .andExpect(jsonPath("$.content.[0].account.name").value("account 30"));
    }

    @Test
    @SneakyThrows
    void addUser() {

    }

    @Test
    void deleteUser() {
    }



    @Test
    void findUserById() {
    }

    @Test
    void findUserByUsername() {
    }
}