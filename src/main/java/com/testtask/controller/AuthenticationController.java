package com.testtask.controller;

import com.testtask.entity.User;
import com.testtask.entity.dto.AuthenticationDto;
import com.testtask.entity.dto.UserDto;
import com.testtask.entity.dto.UserPostDto;
import com.testtask.exception.EntityAlreadyExistsException;
import com.testtask.security.JwtUtils;
import com.testtask.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final UserServiceImpl userService;

  private final PasswordEncoder passwordEncoder;

  private final JwtUtils jwtUtils;

  @PostMapping("/signin")
  @ResponseStatus(HttpStatus.OK)
  public UserDto authUser(@RequestBody AuthenticationDto authenticationDto) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationDto.getEmail(), authenticationDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    User user = (User) authentication.getPrincipal();

    return UserDto.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .email(user.getEmail())
        .enabled(user.isEnabled())
        .token(jwt)
        .build();
  }

  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto registerUser(@RequestBody UserPostDto userPostDto) {
    return userService.create(userPostDto);
  }
}
