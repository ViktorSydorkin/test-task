package com.testtask.service.impl;

import com.testtask.entity.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserServiceImpl userService;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return UserMapper.INSTANCE.fromDto(userService.findByEmail(email));
  }
}
