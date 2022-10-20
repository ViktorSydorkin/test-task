package com.testtask.entity.mappers;

import com.testtask.entity.Department;
import com.testtask.entity.Employee;
import com.testtask.entity.dto.EmployeePostDto;
import com.testtask.entity.dto.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {
  EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

  default EmployeePostDto toDto(Employee employee) {
    return EmployeePostDto.builder()
        .employeeName(employee.getEmployeeName())
        .active(employee.isActive())
        .department(employee.getDepartment().getDepartmentId())
        .build();
  }

  default Employee fromDto(EmployeePostDto employeePostDto) {
    return Employee.builder()
        .employeeName(employeePostDto.getEmployeeName())
        .active(employeePostDto.isActive())
        .department(Department.builder().departmentId(employeePostDto.getDepartment()).build())
        .build();
  }

  default Employee fromDtoWithId(EmployeeDto employeeDto) {
    return Employee.builder()
        .employeeId(employeeDto.getEmployeeId())
        .employeeName(employeeDto.getEmployeeName())
        .active(employeeDto.isActive())
        .department(Department.builder().departmentId(employeeDto.getDepartment()).build())
        .build();
  }

  default EmployeeDto toDtoWithId(Employee employee) {
    return EmployeeDto.builder()
        .employeeId(employee.getEmployeeId())
        .employeeName(employee.getEmployeeName())
        .active(employee.isActive())
        .department(employee.getDepartment().getDepartmentId())
        .build();
  }
}
