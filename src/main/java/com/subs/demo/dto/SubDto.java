package com.subs.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Дто для подписки")
public class SubDto {

    private Long id;
    private String serviceName;
    private ZonedDateTime subscriptionTime;
}
