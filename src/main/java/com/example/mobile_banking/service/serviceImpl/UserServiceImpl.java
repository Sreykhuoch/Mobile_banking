package com.example.mobile_banking.service.serviceImpl;

import com.example.mobile_banking.Repository.RoleRepository;
import com.example.mobile_banking.Repository.UserRepository;
import com.example.mobile_banking.Repository.UserRoleRepository;
import com.example.mobile_banking.api.authority.Role;
import com.example.mobile_banking.api.user.User;
import com.example.mobile_banking.api.user.UserMapper;
import com.example.mobile_banking.api.user.UserRole;
import com.example.mobile_banking.api.user.dto.UpdateUserDto;
import com.example.mobile_banking.api.user.dto.UserDto;
import com.example.mobile_banking.api.user.request.UserRequest;
import com.example.mobile_banking.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Transactional
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;



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
        public UserDto createUserRole(UserRequest userRequest) {
        // Convert UserRequest to User entity
        User newUser = userMapper.createUserDtoToUser(userRequest);
        newUser.setUuid(UUID.randomUUID().toString());
        newUser.setPassword(passwordEncoder.encode("123"));
        newUser.setDeleted(false);
        newUser.setVerified(true);

        // Save the new user to the database
       // User savedUser =  userRepository.saveAndFlush(newUser);
        newUser =  userRepository.save(newUser);
        this.createUserRole(newUser.getId(), userRequest.roleIds());
        userRepository.refresh(newUser);
        // Retrieve the saved user by its ID and return as a DTO
        return userMapper.userToUserDto(newUser);
    }

    @Override
    public UserDto findUserById(Integer id) {
        User  user = userRepository.findById(id).orElseThrow();
        return userMapper.userToUserDto(user);
    }

    @Override
    public void createUserRole(Integer userId, List<Integer> roleId) {
        //fetch roles from the database using the roleIds from the requests
        roleId.forEach(id-> {
            //find role by id that  have input
            Role role = roleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("role id  not found"));

            //handle finding userId
            User  user = userRepository.findById(userId).orElseThrow();
            // Build a new UserRole entity that links the new user with the retrieved role
            UserRole userRole = UserRole.builder()
                    .user(user)
                    .role(role)
                    .build();
            userRoleRepository.save(userRole);
            userRoleRepository.refresh(userRole);
        });
    }

    @Override
    public UserDto findUserByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("id %s is not found", uuid)));
        return userMapper.userToUserDto(user);
    }

    @Override
    public void deleteUserByUuid(String uuid) {
        if(userRepository.existsByUuid(uuid)){
            userRepository.deleteByUuid(uuid);
            return;
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("id %s is not found", uuid));
        }
    }

    @Override
    public void disableUserByUuid(String uuid) {
        userRepository.updateIsDeletedByUuid(true, uuid);
    }

    @Override
    public UserDto updateUserByUuid(String uuid, UpdateUserDto updateUserDto) {

        //reload user by finding it first
        User user = userRepository.findByUuid(uuid).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("id %s is not found", uuid)));
        /*
        //update information
        user.setName(updateUserDto.name());
        user.setGender(updateUserDto.gender());
        user.setIsStudent(updateUserDto.isStudent());
        user.setStudentCardNo(updateUserDto.gender());
         */

        userMapper.updateUserDtoToUser(updateUserDto, user);

        //logic on user role
//        List<UserRole> userRoles = userRoleRepository.findByUser(user);
//        userRoleRepository.deleteAll(userRoles);
        userRoleRepository.deleteByUser(user);    //delete user
        createUserRole(user.getId(), updateUserDto.roleIds());  //after deleted, we created new user.


        //filter yor user role na dea mean id contain nov knoung user role dea ke input mor
        //userRoles : គឺចាំទទួលតម្លៃ
        /*
        *  userRoles = userRoles.stream()
                .filter(userRole -> {
                    //if it's not  contain id dea ke bos mor
                    if(!updateUserDto.roleIds().contains(userRole.getRole().getId())){
                          userRoleRepository.delete(userRole);
                          return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());*/

        //save update user
        user.setUserRole(userRoleRepository.findByUser(user));
        userRepository.save(user);

        //if we want to get the latest update, we need to return it by find it by id
        return findUserById(user.getId());



    }

}

//1:31:46