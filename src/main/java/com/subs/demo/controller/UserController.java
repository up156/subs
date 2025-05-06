package com.subs.demo.controller;

import com.subs.demo.dto.UserDto;
import com.subs.demo.request.UserRequest;
import com.subs.demo.request.UserUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/subs/users")
@Tag(name = "UserController", description = "Контроллер для работы с аккаунтом пользователя")
public interface UserController {

    @GetMapping(value = "/{id}")
    @Operation(summary = "Получение аккаунта по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Аккаунт получен",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = {UserDto.class}))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "404", description = "Аккаунт не найден", content = @Content(mediaType = ""))})
    ResponseEntity<UserDto> getById(@Schema(description = "id Аккаунта") @PathVariable Long id);


    @PostMapping()
    @Operation(summary = "Создание аккаунта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Аккаунт создан",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = {UserDto.class})))})
    ResponseEntity<UserDto> createUser(@RequestBody @Valid UserRequest request);

    @PutMapping("/{id}")
    @Operation(summary = "Обновление аккаунта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Аккаунт обновлен",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = {UserDto.class}))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "401", content = @Content(mediaType = ""))})
    ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserUpdateRequest request);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление аккаунта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Аккаунт удален",
                    content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "404", description = "Аккаунт не найден", content = @Content(mediaType = ""))})
    ResponseEntity<Void> deleteUser(@PathVariable Long id);
}