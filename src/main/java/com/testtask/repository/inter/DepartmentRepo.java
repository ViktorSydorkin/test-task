package com.testtask.repository.inter;

import com.testtask.entity.Department;

import java.util.List;

public interface DepartmentRepo {
    Department getDepartmentById(long id);

    List<Department> getAllDepartments();

    Department updateDepartment(Department department);

    Department addDepartment(Department department);

    void deleteDepartment(long id);
}
