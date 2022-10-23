package com.testtask.controller;

import com.testtask.entity.User;
import com.testtask.entity.dto.AuthenticatedUser;
import com.testtask.entity.dto.AuthenticationDto;
import com.testtask.entity.dto.RegisteredUser;
import com.testtask.entity.dto.UserPostDto;
import com.testtask.entity.mappers.UserMapper;
import com.testtask.security.JwtUtils;
import com.testtask.service.impl.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final UserServiceImpl userService;

  private final JwtUtils jwtUtils;

  @ApiOperation(
          value = "Sign in",
          notes = "Check user's existence in the DB and return token if it is",
          httpMethod = "POST",
          produces = MediaType.APPLICATION_JSON_VALUE)
  @PostMapping("/signin")
  @ResponseStatus(HttpStatus.OK)
  public AuthenticatedUser authUser(@RequestBody @Valid AuthenticationDto authenticationDto) {

    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                authenticationDto.getEmail(), authenticationDto.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);

    User user = (User) authentication.getPrincipal();

    return AuthenticatedUser.builder()
        .userId(user.getUserId())
        .username(user.getUsername())
        .email(user.getEmail())
        .enabled(user.isEnabled())
        .token(jwt)
        .build();
  }
  @ApiOperation(
          value = "Sign up",
          notes = "Save user to the DB",
          httpMethod = "POST",
          produces = MediaType.APPLICATION_JSON_VALUE)
  @PostMapping("/signup")
  @ResponseStatus(HttpStatus.CREATED)
  public RegisteredUser registerUser(@RequestBody @Valid UserPostDto userPostDto) {
    return UserMapper.INSTANCE.toRegisteredUser(userService.create(userPostDto));
  }
}
