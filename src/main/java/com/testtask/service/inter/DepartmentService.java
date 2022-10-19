package com.testtask.service.inter;

import com.testtask.entity.Department;
import com.testtask.entity.dto.DepartmentDTO;
import com.testtask.entity.dto.DepartmentDTOWithId;

import java.util.List;

public interface DepartmentService {
    DepartmentDTOWithId getDepartmentById(long id);
    List<DepartmentDTOWithId> getAllDepartments();
    DepartmentDTOWithId addDepartment(DepartmentDTO departmentDTO);
    DepartmentDTOWithId updateDepartment(DepartmentDTOWithId departmentDTOWithId);
    void deleteDepartment(long id);
}
