package com.SpringSecurity.SpringSecurity.authController;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class authenticateRequest {

    private String email;
    String password;
}
