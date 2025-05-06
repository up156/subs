package com.subs.demo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.ZonedDateTime;

@Builder
@Schema(description = "Запрос на обновление подписки")
public record SubUpdateRequest(
        String serviceName,
        ZonedDateTime subscriptionTime
) {
}
