package com.testtask.repository.inter;

import com.testtask.entity.Department;

import java.util.List;

public interface DepartmentRepo {
    Department getDepartmentById(long id);

    List<Department> getAllDepartments();

    void updateDepartment(Department department);

    void addDepartment(Department department);

    void deleteDepartment(long id);
}
