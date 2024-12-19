package com.example.mobile_banking.base;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ErrorRest<T>(
        Boolean isSuccess,
        String message,
        Integer code,
        LocalDateTime timestamp,
        T errors   // we use generic because we don't know specific types
) {
}
