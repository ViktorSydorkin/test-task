package com.testtask.service.impl;

import com.testtask.repository.inter.DepartmentRepo;
import com.testtask.repository.inter.EmployeeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    DepartmentRepo departmentRepo;

    @InjectMocks
    DepartmentServiceImpl departmentService;

    @Test
    void getDepartmentById() {
    }

    @Test
    void getAllDepartments() {
    }

    @Test
    void addDepartment() {
    }

    @Test
    void updateDepartment() {
    }

    @Test
    void deleteDepartment() {
    }
}