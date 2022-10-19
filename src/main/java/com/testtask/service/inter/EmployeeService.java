package com.testtask.service.inter;

import com.testtask.entity.Employee;
import com.testtask.entity.dto.EmployeeDTO;
import com.testtask.entity.dto.EmployeeDTOWithId;

import java.util.List;


public interface EmployeeService {
    EmployeeDTOWithId getEmployeeById(long id);
    List<EmployeeDTOWithId> getEmployeesByName(String name);
    List<EmployeeDTOWithId> getAllEmployees(int page, int amount);
    EmployeeDTOWithId addEmployee(EmployeeDTO employeeDTO);
    EmployeeDTOWithId updateEmployee(EmployeeDTOWithId employeeDTOWithId);
    void deleteEmployee(long id);
}
