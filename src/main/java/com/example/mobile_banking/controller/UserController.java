package com.example.mobile_banking.controller;

import com.example.mobile_banking.api.user.User;
import com.example.mobile_banking.api.user.dto.UserDto;
import com.example.mobile_banking.base.ApiResponse;
import com.example.mobile_banking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService  userService;

    @GetMapping()
    public ApiResponse<?>  getAllUsers(){
        Iterable<UserDto> userDto = userService.findAll();
        return ApiResponse.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .message("All users have been found")
                .timeStamp(LocalDateTime.now())
                .payload(userDto)
                .build();
    }
}
