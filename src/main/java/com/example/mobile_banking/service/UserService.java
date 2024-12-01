package com.example.mobile_banking.service;

import com.example.mobile_banking.api.user.User;
import com.example.mobile_banking.api.user.dto.UserDto;

import java.util.Iterator;
import java.util.List;

public interface UserService {

    Iterable<UserDto> findAll();
}
