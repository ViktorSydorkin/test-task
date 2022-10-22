package com.testtask.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String email;
    private String token;
    private HttpStatus httpStatus;
}
