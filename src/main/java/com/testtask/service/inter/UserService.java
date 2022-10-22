package com.testtask.service.inter;


import com.testtask.entity.dto.UserDto;
import com.testtask.entity.dto.UserPostDto;

public interface UserService {
    UserDto create(UserPostDto userPostDto);
    UserDto findByEmail(String email);
}
