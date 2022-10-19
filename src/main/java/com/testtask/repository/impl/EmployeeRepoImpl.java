package com.testtask.repository.impl;

import com.testtask.entity.Employee;
import com.testtask.exception.EntityAlreadyExistsException;
import com.testtask.exception.NoSuchEntityException;
import com.testtask.exception.RepositoryException;
import com.testtask.repository.inter.EmployeeRepo;
import com.testtask.repository.util.EmployeeRowMapper;
import com.testtask.repository.util.SQL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmployeeRepoImpl implements EmployeeRepo {
  private final NamedParameterJdbcOperations jdbcTemplate;

  @Override
  public Employee getEmployeeById(long id) {
    try {
      SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
      return jdbcTemplate.queryForObject(SQL.GET_EMPLOYEE_BY_ID, params, new EmployeeRowMapper());
    } catch (DataAccessException dataAccessException) {
      throw new RepositoryException("SQL query went wrong", dataAccessException);
    }
  }

  @Override
  public List<Employee> getEmployeesByName(String name) {
    try {
      SqlParameterSource params = new MapSqlParameterSource().addValue("name", name + "%");
      return jdbcTemplate.query(SQL.GET_EMPLOYEE_BY_NAME, params, new EmployeeRowMapper());
    } catch (DataAccessException dataAccessException) {
      throw new RepositoryException("SQL query went wrong", dataAccessException);
    }
  }

  @Override
  public List<Employee> getAllEmployees(int limit, int offset) {
    try {
      SqlParameterSource params =
          new MapSqlParameterSource().addValue("limit", limit).addValue("offset", offset);
      return jdbcTemplate.query(SQL.GET_ALL_EMPLOYEES, params, new EmployeeRowMapper());
    } catch (DataAccessException dataAccessException) {
      throw new RepositoryException("SQL query went wrong", dataAccessException);
    }
  }

  @Override
  public Employee addEmployee(Employee employee) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    SqlParameterSource params =
        new MapSqlParameterSource()
            .addValue("name", employee.getEmployeeName())
            .addValue("active", employee.isActive())
            .addValue("department", employee.getDepartment().getDepartmentId());
    if (jdbcTemplate.update(SQL.ADD_EMPLOYEE, params, keyHolder, new String[] {"Id"}) == 0)
      throw new EntityAlreadyExistsException(
          "The employee with id " + employee.getEmployeeId() + " already exists");
    employee.setEmployeeId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    return employee;
  }

  @Override
  public Employee updateEmployee(Employee employee) {
    SqlParameterSource params =
        new MapSqlParameterSource()
            .addValue("id", employee.getEmployeeId())
            .addValue("name", employee.getEmployeeName())
            .addValue("active", employee.isActive())
            .addValue("department", employee.getDepartment().getDepartmentId());
    if (jdbcTemplate.update(SQL.UPDATE_EMPLOYEE, params) == 0)
      throw new NoSuchEntityException(
          "The employee with id " + employee.getEmployeeId() + " doesn't exists");
    return employee;
  }

  @Override
  public void deleteEmployee(long id) {
    SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
    if (jdbcTemplate.update(SQL.DELETE_EMPLOYEE, params) == 0)
      throw new NoSuchEntityException("The employee with id " + id + " doesn't exists");
  }
}
