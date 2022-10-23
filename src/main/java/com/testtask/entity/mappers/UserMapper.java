package com.testtask.entity.mappers;

import com.testtask.entity.User;
import com.testtask.entity.dto.AuthenticatedUser;
import com.testtask.entity.dto.RegisteredUser;
import com.testtask.entity.dto.UserDto;
import com.testtask.entity.dto.UserPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  UserDto toDto(User user);

  User fromDto(UserDto userDto);

   User fromPostDto(UserPostDto userPostDto);

   RegisteredUser toRegisteredUser(UserDto userDto);
}
