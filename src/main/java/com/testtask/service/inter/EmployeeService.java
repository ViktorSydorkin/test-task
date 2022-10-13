package com.testtask.service.inter;

import com.testtask.entity.Employee;

import java.util.List;


public interface EmployeeService {
    Employee getEmployeeById(long id);
    List<Employee> getEmployeesByName(String name);
    List<Employee> getAllEmployees(int page, int amount);
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(long id);
}
