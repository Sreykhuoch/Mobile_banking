package com.example.mobile_banking.service.serviceImpl;

import com.example.mobile_banking.Repository.UserRepository;
import com.example.mobile_banking.api.user.User;
import com.example.mobile_banking.api.user.dto.UserDto;
import com.example.mobile_banking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Iterable<UserDto> findAll() {

        //retrieve data from Users

        Iterable<User> users = userRepository.findAll();

        // define new UserDto for ទទួល data from users

        List<UserDto> userDtoList = new ArrayList<>();

        // loop  data  from users and transfer it to userDtoList

        for (User user : users){
            userDtoList.add(UserDto.builder()
                            .uuid(user.getUuid())
                            .name(user.getName())
                            .gender(user.getGender())
                            .email(user.getEmail())
                            .phoneNumber(user.getPhoneNumber())
                            .isStudent(user.isStudent())
                            .studentCardNo(user.getStudentCardNo())
                    .build());
        }
        return userDtoList;
    }
}
