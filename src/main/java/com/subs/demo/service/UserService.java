package com.subs.demo.service;

import com.subs.demo.dto.UserDto;
import com.subs.demo.request.UserRequest;
import com.subs.demo.request.UserUpdateRequest;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "UserService", description = "Сервис для работы с аккаунтом пользователя")
public interface UserService {

    UserDto getById(Long id);

    UserDto createUser(UserRequest request);

    UserDto update(Long id, UserUpdateRequest request);

    void deleteUser(Long id);
}