package com.subs.demo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(description = "Запрос на обновление аккаунта")
public record UserUpdateRequest(
        String name,
        String email
) {
}
