package com.example.mobile_banking.service.serviceImpl;

import com.example.mobile_banking.Repository.RoleRepository;
import com.example.mobile_banking.Repository.UserRepository;
import com.example.mobile_banking.Repository.UserRoleRepository;
import com.example.mobile_banking.api.authority.Role;
import com.example.mobile_banking.api.user.User;
import com.example.mobile_banking.api.user.UserMapper;
import com.example.mobile_banking.api.user.UserRole;
import com.example.mobile_banking.api.user.dto.UserDto;
import com.example.mobile_banking.api.user.request.UserRequest;
import com.example.mobile_banking.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    //inject userMapper

    private final UserMapper userMapper;

    /*   no need to write this line of code, when we use model mapper
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
    }  */


    @Override
    public Iterable<UserDto> findAll() {
        Iterable<User> users = userRepository.findAll();
        //convert list of users to list of userDto by using UserMapper interface
        return userMapper.usersToUserDto(users);

    }

    @Override
    public UserDto createUser(UserRequest userRequest) {
        // Convert UserRequest to User entity
        User newUser = userMapper.createUserDtoToUser(userRequest);
        newUser.setUuid(UUID.randomUUID().toString());
        newUser.setStudent(false);
        newUser.setDeleted(false);
        newUser.setVerified(true);

        // Save the new user to the database
        userRepository.save(newUser);

        Role customerRole = roleRepository.findById(3).orElseThrow(() -> new IllegalArgumentException("Role id  is not  found"));

        UserRole userRoleCustomer = UserRole.builder()
                .user(newUser)
                .role(customerRole  )
                .build();
        userRoleRepository.save(userRoleCustomer);

        // Retrieve the saved user by its ID and return as a DTO
        return findUserById(newUser.getId());
    }

    @Override
    public UserDto findUserById(Integer id) {
        User  user = userRepository.findById(id).orElseThrow();
        return userMapper.userToUserDto(user);
    }


}
