package com.subs.demo.controller;

import com.subs.demo.dto.SubDto;
import com.subs.demo.dto.SubStatDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/subs/stats/top")
@Tag(name = "SubController", description = "Контроллер для работы со статистикой по подпискам")
public interface SubStatsController {

    @GetMapping
    @Operation(summary = "Получение самых популярных подписок сервиса")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Список популярных подписок получен",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(allOf = {SubDto.class}))),
            @ApiResponse(responseCode = "404", description = "Не найдено", content = @Content(mediaType = ""))})
    ResponseEntity<List<SubStatDto>> getTopSubs();

}