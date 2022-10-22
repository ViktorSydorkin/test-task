package com.testtask.service.impl;

import com.testtask.entity.User;
import com.testtask.entity.dto.UserDto;
import com.testtask.entity.dto.UserPostDto;
import com.testtask.entity.mappers.UserMapper;
import com.testtask.repository.inter.UserRepo;
import com.testtask.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepo userRepo;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserDto create(UserPostDto userPostDto) {
    User user = UserMapper.INSTANCE.fromPostDto(userPostDto);
    user.setPassword(passwordEncoder.encode(userPostDto.getPassword()));
    user.setEnabled(true);
    log.info("User is going to be created");
    return UserMapper.INSTANCE.toDto(userRepo.create(user));
  }

  @Override
  public UserDto findByEmail(String email) {
    log.info("User is going to be found by email");
    return UserMapper.INSTANCE.toDto(userRepo.findByEmail(email));
  }
}
