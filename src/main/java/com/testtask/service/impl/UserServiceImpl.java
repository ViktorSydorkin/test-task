package com.testtask.service.impl;

import com.testtask.entity.dto.UserDto;
import com.testtask.entity.dto.UserPostDto;
import com.testtask.entity.mappers.UserMapper;
import com.testtask.repository.inter.UserRepo;
import com.testtask.service.inter.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepo userRepo;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public UserDto create(UserPostDto userPostDto) {
    userPostDto.setPassword(passwordEncoder.encode(userPostDto.getPassword()));
    userPostDto.setEnabled(true);

    return UserMapper.INSTANCE.toDto(userRepo.create(UserMapper.INSTANCE.fromPostDto(userPostDto)));
  }

  @Override
  public UserDto findByEmail(String email) {
    return UserMapper.INSTANCE.toDto(userRepo.findByEmail(email));
  }
}
