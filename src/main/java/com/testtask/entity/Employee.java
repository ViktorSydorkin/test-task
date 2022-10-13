package com.testtask.entity;

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
public class Employee {
  private long employee_id;
  @NotBlank private String employee_name;
  @NotNull private boolean active;
  @NotNull private Department department;
}
