package com.subs.demo.controller;

import com.subs.demo.dto.SubDto;
import com.subs.demo.request.SubRequest;
import com.subs.demo.request.SubUpdateRequest;
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

import java.util.List;

@RestController
@RequestMapping("api/v1/subs/users/{userId}/subs")
@Tag(name = "SubController", description = "Контроллер для работы с подписками пользователя")
public interface SubController {

    @GetMapping
    @Operation(summary = "Получение всех подписок по id пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Подписка получена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = {SubDto.class}))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(mediaType = ""))})
    ResponseEntity<List<SubDto>> getById(@Schema(description = "id пользователя") @PathVariable Long userId);

    @PostMapping
    @Operation(summary = "Создание подписки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Подписка создана",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = {SubDto.class})))})
    ResponseEntity<SubDto> create(@PathVariable Long userid, @RequestBody @Valid SubRequest request);

    @PutMapping("/{subId}")
    @Operation(summary = "Обновление подписки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Подписка обновлена",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = {SubDto.class}))),
            @ApiResponse(responseCode = "400", content = @Content(mediaType = ""))})
    ResponseEntity<SubDto> update(@PathVariable Long userId, @PathVariable Long subId, @RequestBody @Valid SubUpdateRequest request);

    @DeleteMapping("/{subId}")
    @Operation(summary = "Удаление подписки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Подписка удалена",
                    content = @Content(mediaType = "")),
            @ApiResponse(responseCode = "404", description = "Подписка не найдена", content = @Content(mediaType = ""))})
    ResponseEntity<Void> delete(@PathVariable Long userId, @PathVariable Long subId);
}