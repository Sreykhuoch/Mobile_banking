package com.example.mobile_banking.api.user.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

//this class is where we want user to see the data, so we provide only necessary data.
@Builder
public record UserDto(
        String uuid,
        String name,
        String gender,
        String email,
        String phoneNumber,
        Boolean isStudent,
        @JsonInclude(value = JsonInclude.Include.NON_NULL)
        String studentCardNo,
        List<UserRoleDto> userRole
) {
}
