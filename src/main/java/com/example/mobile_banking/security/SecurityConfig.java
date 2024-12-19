package com.example.mobile_banking.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder);

        return auth;
    }

    //create bean : security filter chain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //disable csrf : disable វាចេញ ដើម្បីកុំអោយ attacker អាច attack ចូលបាន
        // If  we dont disable, our application  will be stateless and can only  work in server
        http.csrf(AbstractHttpConfigurer::disable);

        //Security Mechanism
        //start to config -> យើងកំំណត់ថាយើងអាចធ្វើអិបានខ្លះ
        http.authorizeHttpRequests(auth->{
            auth.anyRequest().authenticated();
        });

        //HttpBasic Authentication (នៅពេលដែលវាមានលក្ខណះ stateless)
       // HTTP Basic Authentication is a mechanism where the client sends a username and password in the Authorization header of the HTTP request.
        //http.httpBasic(Customizer.withDefaults());

        // now we are using security mechanism  with JWT (Stateless)
        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(
                jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(null)
        ));


        //make our  http STATELESS : mean that it doesn't store data  inn cache
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }
}
