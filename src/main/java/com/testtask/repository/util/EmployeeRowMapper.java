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
        .employee_id(rs.getLong("employee_id"))
        .employee_name(rs.getString("name"))
        .active(rs.getBoolean("active"))
        .department(
            Department.builder()
                .department_id(rs.getLong("department_id"))
                .department_name(rs.getString("department_name"))
                .build())
        .build();
  }
}
