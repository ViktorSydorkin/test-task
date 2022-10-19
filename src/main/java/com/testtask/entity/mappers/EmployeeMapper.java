package com.testtask.entity.mappers;

import com.testtask.entity.Department;
import com.testtask.entity.Employee;
import com.testtask.entity.dto.EmployeeDTO;
import com.testtask.entity.dto.EmployeeDTOWithId;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  default EmployeeDTO toDto(Employee employee) {
    return EmployeeDTO.builder()
        .employeeName(employee.getEmployeeName())
        .active(employee.isActive())
        .department(employee.getDepartment().getDepartmentId())
        .build();
  }

  default Employee fromDto(EmployeeDTO employeeDTO) {
    return Employee.builder()
        .employeeName(employeeDTO.getEmployeeName())
        .active(employeeDTO.isActive())
        .department(Department.builder().departmentId(employeeDTO.getDepartment()).build())
        .build();
  }

  default Employee fromDtoWithId(EmployeeDTOWithId employeeDTOWithId) {
    return Employee.builder()
        .employeeId(employeeDTOWithId.getEmployeeId())
        .employeeName(employeeDTOWithId.getEmployeeName())
        .active(employeeDTOWithId.isActive())
        .department(Department.builder().departmentId(employeeDTOWithId.getDepartment()).build())
        .build();
  }

  default EmployeeDTOWithId toDtoWithId(Employee employee) {
    return EmployeeDTOWithId.builder()
        .employeeId(employee.getEmployeeId())
        .employeeName(employee.getEmployeeName())
        .active(employee.isActive())
        .department(employee.getDepartment().getDepartmentId())
        .build();
  }
}
