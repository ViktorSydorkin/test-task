package com.testtask.entity.dto;

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
public class EmployeeDTOWithId {
    @NotNull
    @Positive
    public long employeeId;
    @NotBlank
    public String employeeName;
    @NotNull
    public boolean active;
    @NotNull public long department;
}
