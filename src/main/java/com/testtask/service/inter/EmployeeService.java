package com.testtask.service.inter;

import com.testtask.entity.dto.EmployeePostDto;
import com.testtask.entity.dto.EmployeeDto;

import java.util.List;


public interface EmployeeService {
    EmployeeDto findById(long id);
    List<EmployeeDto> findByName(String name);
    List<EmployeeDto> findAll(int page, int amount);
    EmployeeDto create(EmployeePostDto employeePostDto);
    EmployeeDto update(EmployeeDto employeeDto);
    void deleteById(long id);
}
