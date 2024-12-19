package com.example.mobile_banking.api.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateUserDto(

        @NotBlank
        String name,

        @NotBlank
        String gender,

        @NotNull
        Boolean isStudent,

        String studentCardNo,

        List<Integer> roleIds

) {
}
