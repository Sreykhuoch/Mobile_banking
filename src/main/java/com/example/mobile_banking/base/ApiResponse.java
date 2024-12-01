package com.example.mobile_banking.base;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ApiResponse<T>(
        Boolean isSuccess,
        Integer code,
        String message,
        LocalDateTime timeStamp,
        T payload
) {
}
