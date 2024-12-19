package com.example.mobile_banking.controller;


import com.example.mobile_banking.Repository.RoleRepository;
import com.example.mobile_banking.api.authority.Role;
import com.example.mobile_banking.base.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authorities")
@RequiredArgsConstructor
public class AuthorityRestController {

    private final RoleRepository roleRepository;

    @GetMapping
    public ApiResponse<?> findAuthority(){
        List<Role> roles = roleRepository.findAll();
         return ApiResponse.builder()
                .isSuccess(true)
                .code(HttpStatus.OK.value())
                .message("users has been updated ")
                .timeStamp(LocalDateTime.now())
                .payload(roles)
                .build();
    }
}
