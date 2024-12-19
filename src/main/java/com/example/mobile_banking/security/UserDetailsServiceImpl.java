package com.example.mobile_banking.security;

import com.example.mobile_banking.Repository.UserRepository;
import com.example.mobile_banking.api.user.User;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user =  userRepository.findByEmailAndDeletedIsFalse(email).orElseThrow(
                () -> new UsernameNotFoundException("your username is not found !! ")
        );

        CustomerUserDetails customerUserDetails = new CustomerUserDetails();
        customerUserDetails.setUser(user);
        return customerUserDetails;
    }
}
