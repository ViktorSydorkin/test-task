package com.testtask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
  @Positive
  private long employeeId;
  @NotBlank private String employeeName;
  @NotNull private boolean active;
  @NotNull private Department department;
}
