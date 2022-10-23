package com.testtask.entity.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class AuthenticationDto {
  @Email public String email;

  @NotBlank
  @Size(min = 5, max = 30)
  public String password;
}
