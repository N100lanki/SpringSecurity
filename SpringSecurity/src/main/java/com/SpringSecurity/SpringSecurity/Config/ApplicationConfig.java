package com.SpringSecurity.SpringSecurity.Config;

import com.SpringSecurity.SpringSecurity.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepo userRepo;

//     User Details Service only has access to the username in order to retrieve the full user entity
    @Bean
    public UserDetailsService userDetailsService(){
         return username -> userRepo.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("User not found Dost "));
    }
// an Authentication request is processed by an AuthenticationProvider, and a fully authenticated object with full credentials is returned.
    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

//     Create for Password encoder
    @Bean
    public  PasswordEncoder passwordEncoder() {
     return   new BCryptPasswordEncoder();
    }


//it is used to manage authentication of users
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return  config.getAuthenticationManager();

    }

}
