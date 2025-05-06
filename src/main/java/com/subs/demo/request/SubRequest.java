package com.subs.demo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
@Schema(description = "Запрос на создание подписки")
public record SubRequest(
        @NotBlank String serviceName,
        @NotNull ZonedDateTime subscriptionTime
) {
}
