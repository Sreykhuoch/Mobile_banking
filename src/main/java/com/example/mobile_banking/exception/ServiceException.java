package com.example.mobile_banking.exception;

import com.example.mobile_banking.base.ErrorRest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ServiceException {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ResponseStatusException.class)
    public ErrorRest<?> handleServiceException(ResponseStatusException e)
    {
        return ErrorRest.builder()
                .isSuccess(false)
                .message("server has been errored, please checked !!")
                .code(HttpStatus.NOT_FOUND.value())
                .timestamp(LocalDateTime.now())
                .errors(e.getReason())
                .build();
    }
}