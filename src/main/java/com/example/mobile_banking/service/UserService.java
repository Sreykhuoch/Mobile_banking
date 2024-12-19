package com.example.mobile_banking.service;

import com.example.mobile_banking.api.user.dto.UpdateUserDto;
import com.example.mobile_banking.api.user.dto.UserDto;
import com.example.mobile_banking.api.user.request.UserRequest;

import java.util.List;

public interface UserService {

    Iterable<UserDto> findAll();

    UserDto createUserRole(UserRequest userRequest);

    UserDto findUserById(Integer id);

    void createUserRole(Integer userId, List<Integer> roleId);

    UserDto findUserByUuid(String uuid);

    void deleteUserByUuid(String uui);

    void disableUserByUuid(String uuid);

    UserDto updateUserByUuid(String uuid, UpdateUserDto updateUserDto);
}
