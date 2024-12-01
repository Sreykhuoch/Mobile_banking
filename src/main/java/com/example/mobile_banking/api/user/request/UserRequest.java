package com.example.mobile_banking.api.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.util.List;

public record UserRequest(
        @NotBlank
        @Size(min = 6, max = 30)
        String name,

        @NotBlank
        String  gender,


        @NotBlank
        @Email
        String  email,

        @NotBlank
        @Size(min = 8, max = 25)
        String password,
        String phoneNumber,
        List<Integer> roleId
) {
}
