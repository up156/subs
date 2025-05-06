package com.subs.demo.service.impl;

import com.subs.demo.dto.UserDto;
import com.subs.demo.ex.UserNotFoundException;
import com.subs.demo.mapper.UserMapper;
import com.subs.demo.model.User;
import com.subs.demo.repository.UserRepository;
import com.subs.demo.request.UserRequest;
import com.subs.demo.request.UserUpdateRequest;
import com.subs.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    static final String NOT_FOUND_MESSAGE = "Not found: no user with id: ";

    @Override
    @Transactional(readOnly = true)
    public UserDto getById(Long id) {

        log.info("User service started getById with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_MESSAGE + id));
        return userMapper.mapToUserDto(user);
    }

    @Override
    @Transactional
    public UserDto createUser(UserRequest request) {

        log.info("User service started createUser with request: {}", request);
        User toSave = userMapper.mapToUser(request);
        return userMapper.mapToUserDto(userRepository.save(toSave));
    }

    @Override
    @Transactional
    public UserDto update(Long id, UserUpdateRequest request) {

        log.info("User service started update with userId: {} and with request: {}", id, request);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(NOT_FOUND_MESSAGE + id));
        userMapper.updateUser(user, request);
        return userMapper.mapToUserDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(NOT_FOUND_MESSAGE + id);
        }
        userRepository.deleteById(id);
    }
}
