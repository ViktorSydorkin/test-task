package com.testtask.service.inter;

import com.testtask.entity.dto.DepartmentDto;
import com.testtask.entity.dto.DepartmentPostDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto findById(long id);
    List<DepartmentDto> findAll();
    DepartmentDto create(DepartmentPostDto departmentPostDto);
    DepartmentDto update(DepartmentDto departmentDto);
    void deleteById(long id);
}
