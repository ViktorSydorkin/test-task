package com.testtask.repository.impl;

import com.testtask.eception.RepositoryException;
import com.testtask.entity.Employee;
import com.testtask.repository.inter.EmployeeRepo;
import com.testtask.repository.util.EmployeeRowMapper;
import com.testtask.repository.util.SQL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class EmployeeRepoImpl implements EmployeeRepo {
  private final NamedParameterJdbcOperations jdbcTemplate;

  @Override
  public Employee getEmployeeById(long id) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      return jdbcTemplate.queryForObject(SQL.GET_EMPLOYEE_BY_ID, params, new EmployeeRowMapper());
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }

  @Override
  public List<Employee> getEmployeesByName(String name) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("name", name + "%");
      return jdbcTemplate.query(SQL.GET_EMPLOYEE_BY_NAME, params, new EmployeeRowMapper());
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }

  @Override
  public List<Employee> getAllEmployees(int limit, int offset) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("limit", limit);
      params.put("offset", offset);
      return jdbcTemplate.query(SQL.GET_ALL_EMPLOYEES, params, new EmployeeRowMapper());
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }

  @Override
  public void addEmployee(Employee employee) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("name", employee.getEmployee_name());
      params.put("active", employee.isActive());
      params.put("department", employee.getDepartment().getDepartment_id());
      if (jdbcTemplate.update(SQL.ADD_EMPLOYEE, params) == 0)
        throw new RepositoryException(
            "The employee with id " + employee.getEmployee_id() + " already exists");
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }

  @Override
  public void updateEmployee(Employee employee) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", employee.getEmployee_id());
      params.put("name", employee.getEmployee_name());
      params.put("active", employee.isActive());
      params.put("department", employee.getDepartment().getDepartment_id());
      if (jdbcTemplate.update(SQL.UPDATE_EMPLOYEE, params) == 0)
        throw new RepositoryException(
            "The employee with id " + employee.getEmployee_id() + " doesn't exists");
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }

  @Override
  public void deleteEmployee(long id) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      if (jdbcTemplate.update(SQL.DELETE_EMPLOYEE, params) == 0)
        throw new RepositoryException("The employee with id " + id + " doesn't exists");
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }
}
