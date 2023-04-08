package com.SpringSecurity.SpringSecurity.Service;


import com.SpringSecurity.SpringSecurity.Config.JwtService;
import com.SpringSecurity.SpringSecurity.Entity.Role;
import com.SpringSecurity.SpringSecurity.Entity.User;
import com.SpringSecurity.SpringSecurity.Repository.UserRepo;
import com.SpringSecurity.SpringSecurity.authController.AuthenticationResponse;
import com.SpringSecurity.SpringSecurity.authController.RegisterRequest;
import com.SpringSecurity.SpringSecurity.authController.authenticateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.springframework.security.core.userdetails.User.*;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private  final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register( RegisterRequest request) {
        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);
      var jwttoken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwttoken).build();

    }

    public AuthenticationResponse authenticate(authenticateRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken( request.getEmail(),request.getPassword())

        );
        var user =userRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwttoken=jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwttoken).build();



    }
}
