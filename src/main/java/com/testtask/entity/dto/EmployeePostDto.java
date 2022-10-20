package com.testtask.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePostDto {
  @NotBlank public String employeeName;
  @NotNull public boolean active;
  @NotNull public long department;
}
