package com.testtask.repository.util;

import com.testtask.entity.Department;
import com.testtask.entity.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
  @Override
  public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

    return Employee.builder()
        .employeeId(rs.getLong("employee_id"))
        .employeeName(rs.getString("name"))
        .active(rs.getBoolean("active"))
        .department(
            Department.builder()
                .departmentId(rs.getLong("department_id"))
                .departmentName(rs.getString("department_name"))
                .build())
        .build();
  }
}
