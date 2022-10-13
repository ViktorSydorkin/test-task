package com.testtask.service.inter;

import com.testtask.entity.Department;

import java.util.List;

public interface DepartmentService {
    Department getDepartmentById(long id);
    List<Department> getAllDepartments();
    void addDepartment(Department department);
    void updateDepartment(Department department);
    void deleteDepartment(long id);
}
