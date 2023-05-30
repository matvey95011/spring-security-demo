package ru.example.java.spring.demo.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.example.java.spring.demo.entity.User;
import ru.example.java.spring.demo.mapper.UserMapper;
import ru.example.java.spring.demo.repository.UserRepository;
import ru.example.java.spring.model.AuthenticationUserResponse;
import ru.example.java.spring.model.FilterRequest;
import ru.example.java.spring.model.PageUserResponse;
import ru.example.java.spring.model.UserRequest;
import ru.example.java.spring.model.UserResponse;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    @Override
    @Transactional
    public UserResponse add(UserRequest request) {
        User user = getUserOrCreate(request);
        return mapper.toUserResponse(userRepository.save(user));
    }

    private User getUserOrCreate(UserRequest request) {
        return request.getId() == null && !userRepository.existsByUsername(request.getUsername()) ?
                mapper.toEntity(request) :
                userRepository.findById(request.getId())
                        .map(user -> mapper.toEntity(user, request))
                        .orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    @Override
    public void delete(UUID id) {
        userRepository.findById(id).ifPresent(userRepository::delete);
    }

    @Override
    public PageUserResponse findAllUser(FilterRequest filter) {
        return null; // todo:
    }

    @Override
    public UserResponse findById(UUID id) {
        return userRepository.findById(id).map(mapper::toUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }

    @Override
    public AuthenticationUserResponse findByUsername(String username) {
        return userRepository.findByUsername(username).map(mapper::toAuthUser)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }
}
