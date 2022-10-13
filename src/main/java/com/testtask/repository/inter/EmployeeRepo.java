package com.testtask.repository.inter;

import com.testtask.entity.Employee;

import java.util.List;

public interface EmployeeRepo {
    Employee getEmployeeById(long id);

    List<Employee> getEmployeesByName(String name);

    List<Employee> getAllEmployees(int limit, int offset);

    void addEmployee(Employee employee);

    void updateEmployee(Employee employee);

    void deleteEmployee(long id);
}
