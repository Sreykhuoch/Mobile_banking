package com.example.mobile_banking.security;


import com.example.mobile_banking.api.user.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CustomerUserDetails implements UserDetails {

    private User user;


    //This method returns a collection of GrantedAuthority objects,
    // which represent the roles or permissions granted to the user.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        //add role ដែល user និងមាន
        user.getUserRole().forEach(userRole -> {

            // add role (authority  container) to SimpleGrantedAuthority list
            //example : loop1 : user add role admin
            authorities.add(new SimpleGrantedAuthority(userRole.getRole().getAuthority()));

            // add authorities (Authority container ) to SimpleGrantedAuthority list
            // after that, they add permission of what admin can do
            userRole.getRole().getAuthorities().forEach(
                    authority -> {
                        authorities.add(new SimpleGrantedAuthority(authority.getName()));
                    }
            );
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isDeleted();
    }
}
