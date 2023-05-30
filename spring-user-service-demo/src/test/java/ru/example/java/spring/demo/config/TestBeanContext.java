package ru.example.java.spring.demo.config;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import ru.example.java.spring.demo.repository.UserRepository;

@Getter
@Builder
@Accessors(fluent = true)
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TestBeanContext {

    UserRepository userRepository;

}
