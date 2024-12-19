package com.example.mobile_banking.exception;

import com.example.mobile_banking.base.ErrorRest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {
    // so we need to find which one is causing exception

    @ResponseStatus(HttpStatus.BAD_REQUEST)   // ធ្វើការបញ្ជក់នូវ response status របស់យើង ដោយយើងអាចdefine ខ្លួនឯងបាន
    @ExceptionHandler(MethodArgumentNotValidException.class) // mean that we handle on MethodArgumentNotValidException
    public ErrorRest<?> handleException(MethodArgumentNotValidException ex) { // to ចាប់តម្លៃ error បាន យើងត្រូវ bos vea jea parameter

        List<Map<String, String>> errors = new ArrayList<>();

        for (FieldError fieldError : ex.getFieldErrors()) {    // to get  error in each object
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("field", fieldError.getField());
            errorMap.put("message", fieldError.getDefaultMessage());

            errors.add(errorMap);
        }

//        return new ErrorRest<>(
//                false,
//                "Validation has errors...!",
//                HttpStatus.BAD_GATEWAY.value(),
//                LocalDateTime.now(),
//                errors
//        );


        //using builder
        return ErrorRest.builder()
                .isSuccess(false)
                .message("validate has errors")
                .code(HttpStatus.BAD_REQUEST.value())
                .timestamp(LocalDateTime.now())
                .errors(errors)
                .build();
    }
}
