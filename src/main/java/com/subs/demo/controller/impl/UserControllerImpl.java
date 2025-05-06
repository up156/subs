package com.subs.demo.controller.impl;

import com.subs.demo.controller.UserController;
import com.subs.demo.dto.UserDto;
import com.subs.demo.request.UserRequest;
import com.subs.demo.request.UserUpdateRequest;
import com.subs.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<UserDto> getById(Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @Override
    public ResponseEntity<UserDto> createUser(UserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }

    @Override
    public ResponseEntity<UserDto> update(Long id, UserUpdateRequest request) {
        return ResponseEntity.ok(userService.update(id, request));
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
