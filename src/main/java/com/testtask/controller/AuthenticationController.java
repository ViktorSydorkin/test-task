package com.testtask.controller;

import com.testtask.entity.dto.AuthenticationDto;
import com.testtask.entity.dto.UserDto;
import com.testtask.security.AuthenticationResponse;
import com.testtask.security.TokenProvider;
import com.testtask.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {
  private final AuthenticationManager authenticationManager;
  private final TokenProvider tokenProvider;
  private final UserService userService;
  @PostMapping("/login")
  public AuthenticationResponse login(@RequestParam AuthenticationDto authenticationDto) {
    try {
      String email = authenticationDto.getEmail();
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(email, authenticationDto.getPassword()));
      UserDto user = userService.findByEmail(email);
      if (user == null) {
        throw new UsernameNotFoundException("User with username: " + email + " not found");
      }

      String token = tokenProvider.createToken(email);
      return new AuthenticationResponse(email, token, HttpStatus.OK);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }
}
