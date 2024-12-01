package com.example.mobile_banking.init;

import com.example.mobile_banking.Repository.AuthorityRepo;
import com.example.mobile_banking.Repository.RoleRepository;
import com.example.mobile_banking.Repository.UserRepository;
import com.example.mobile_banking.Repository.UserRoleRepository;
import com.example.mobile_banking.api.authority.Authority;
import com.example.mobile_banking.api.authority.Role;
import com.example.mobile_banking.api.user.User;
import com.example.mobile_banking.api.user.UserRole;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component   //initialize data when spring app start/ សម្រាប់ initialize ណាដែលចាំបាច់
@AllArgsConstructor
public class DataInitialization {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthorityRepo authorityRepo;
    private final UserRoleRepository userRoleRepository;

    @PostConstruct   // this method will work when the spring application started
    public void init() {

        //provide permission and limitation to user
        Authority userRead = Authority.builder().name("user:read").build();
        Authority userWrite = Authority.builder().name("user:write").build();
        Authority userDelete = Authority.builder().name("user:delete").build();
        Authority userUpdate = Authority.builder().name("user:update").build();

        Authority accountRead =  Authority.builder().name("account:read").build();
        Authority accountWrite= Authority.builder().name("account:write").build();
        Authority accountDelete= Authority.builder().name("account:delete").build();
        Authority accountUpdate= Authority.builder().name("account:update").build();


        Authority transactionRead= Authority.builder().name("transaction:write").build();
        Authority transactionWrite= Authority.builder().name("transaction:delete").build();
        Authority transactionUpdate= Authority.builder().name("transaction:update").build();
        Authority transactionDelete= Authority.builder().name("transaction:delete").build();

        List<Authority> authorities = List.of(
             userRead, userWrite, userDelete, userUpdate, accountRead,
                accountWrite, accountUpdate, accountDelete,
                transactionRead, transactionWrite, transactionUpdate, transactionDelete
        );

        authorityRepo.saveAll(authorities);

        Role roleAdmin = Role.builder()
                .name("ADMIN")
                .authorities(authorities)
                .build();
        Role roleManager = Role.builder()
                        .name("MANAGER")
                        .authorities(List.of(userRead, userDelete, userUpdate, accountRead
                                , accountUpdate, accountDelete,
                                transactionRead, transactionUpdate, transactionDelete))
                        .build();
        Role roleCustomer = Role.builder()
                        .name("CUSTOMER")
                        .authorities(List.of(userRead, userWrite, userUpdate, accountRead
                                , accountUpdate, accountWrite,
                                transactionRead, transactionWrite))
                        .build();

        List<Role> roles = List.of(
               roleAdmin, roleManager, roleCustomer
        );

        roleRepository.saveAll(roles);



        User user = User.builder()
                .uuid(UUID.randomUUID().toString())
                .name("Administrator")
                .email("adminsitractor@gmail.com")
                .gender("Male")
                .phoneNumber("068279880")
                .isVerified(true)
                .isDeleted(false)
                .isStudent(true)
                .build();

        //saveAll() expects a collection of entities not a single entity
        userRepository.save(user);



//        UserRole userRoleAdmin = UserRole.builder()
//                .user(user)
//                .id(1)
//                .role(roleAdmin)
//                .build();
//        userRoleRepository.save(userRoleAdmin);
    }
}


//i::44:52
