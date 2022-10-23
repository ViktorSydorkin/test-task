package com.testtask.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPostDto {
  @NotBlank public String username;
  @Email public String email;

  @NotBlank
  @Size(min = 5, max = 30)
  public String password;
}
