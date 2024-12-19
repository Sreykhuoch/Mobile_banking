package com.example.mobile_banking.controller;

import com.example.mobile_banking.api.user.dto.UpdateUserDto;
import com.example.mobile_banking.api.user.dto.UserDto;
import com.example.mobile_banking.api.user.request.UserRequest;
import com.example.mobile_banking.base.ApiResponse;
import com.example.mobile_banking.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping()
    public  ApiResponse<?>  createUser(@Valid  @RequestBody UserRequest userRequest){
        UserDto  newUser = userService.createUserRole(userRequest);
        return ApiResponse.builder()
                .isSuccess(true)
                .code(HttpStatus.CREATED.value())
                .message("user have been created  successfully")
                .timeStamp(LocalDateTime.now())
                .payload(newUser)
                .build();
    }

    @GetMapping("/{uuid}")
    public ApiResponse<?> getUserByUuid(@PathVariable String uuid){
        UserDto userDto = userService.findUserByUuid(uuid);
        return ApiResponse.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .message("users has been found bby id ")
                .timeStamp(LocalDateTime.now())
                .payload(userDto)
                .build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)  //mean that if it's success, there's nothing to return
    @DeleteMapping("/{uuid}")
    public void deleteUserByUuid(@PathVariable String uuid){
        userService.deleteUserByUuid(uuid);

    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{uuid}/disable")
    public void disableUserByUuid(@PathVariable String uuid){
        userService.disableUserByUuid(uuid);
    }

    @PutMapping("/{uuid}")
    public ApiResponse<?> updateUserByUuid(@PathVariable String uuid, @RequestBody UpdateUserDto updateUserDto){
        UserDto  updateUser  = userService.updateUserByUuid(uuid, updateUserDto);
        return ApiResponse.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .message("users has been updated ")
                .timeStamp(LocalDateTime.now())
                .payload(updateUser)
                .build();
    }



}
