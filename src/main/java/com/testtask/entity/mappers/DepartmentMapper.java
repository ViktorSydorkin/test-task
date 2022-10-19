package com.testtask.entity.mappers;

import com.testtask.entity.Department;
import com.testtask.entity.dto.DepartmentDTO;
import com.testtask.entity.dto.DepartmentDTOWithId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);
    DepartmentDTO toDto(Department department);
    Department fromDto(DepartmentDTO departmentDTO);
    DepartmentDTOWithId toDtoWithId(Department department);
    Department fromDtoWithId(DepartmentDTOWithId departmentDTO);
}
