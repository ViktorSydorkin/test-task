package com.testtask.repository.inter;

import com.testtask.entity.Employee;

import java.util.List;

public interface EmployeeRepo {
    Employee getEmployeeById(long id);

    List<Employee> getEmployeesByName(String name);

    List<Employee> getAllEmployees(int limit, int offset);

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployee(long id);
}
