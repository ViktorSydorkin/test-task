package com.testtask.entity.mappers;

import com.testtask.entity.Department;
import com.testtask.entity.dto.DepartmentDto;
import com.testtask.entity.dto.DepartmentPostDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    DepartmentPostDto toPostDto(Department department);
    Department fromPostDto(DepartmentPostDto departmentPostDto);
    DepartmentDto toDto(Department department);
    Department fromDto(DepartmentDto departmentDTO);
}
