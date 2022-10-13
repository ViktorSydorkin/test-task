package com.testtask.repository.impl;

import com.testtask.eception.RepositoryException;
import com.testtask.entity.Department;
import com.testtask.repository.inter.DepartmentRepo;
import com.testtask.repository.util.SQL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DepartmentRepoImpl implements DepartmentRepo {
  private final NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Department getDepartmentById(long id) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      return jdbcTemplate.queryForObject(
          SQL.GET_DEPARTMENT_BY_ID, params, new BeanPropertyRowMapper<>(Department.class));
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }

  @Override
  public List<Department> getAllDepartments() {
    try {
      return jdbcTemplate.query(
          SQL.GET_ALL_DEPARTMENTS, new BeanPropertyRowMapper<>(Department.class));
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }

  @Override
  public void updateDepartment(Department department) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", department.getDepartment_id());
      params.put("name", department.getDepartment_name());
      if (jdbcTemplate.update(SQL.UPDATE_DEPARTMENT, params) == 0)
        throw new RepositoryException(
            "The department with id " + department.getDepartment_id() + " doesn't exists");
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }

  @Override
  public void addDepartment(Department department) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("name", department.getDepartment_name());
      if (jdbcTemplate.update(SQL.ADD_DEPARTMENT, params) == 0)
        throw new RepositoryException(
            "The department with id " + department.getDepartment_id() + " already exists");
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }

  @Override
  public void deleteDepartment(long id) {
    try {
      Map<String, Object> params = new HashMap<>();
      params.put("id", id);
      if (jdbcTemplate.update(SQL.DELETE_DEPARTMENT, params) == 0)
        throw new RepositoryException("The department with id " + id + " doesn't exists");
    } catch (Exception e) {
      log.error("Repository has thrown an exception");
      throw new RepositoryException(e.getMessage());
    }
  }
}
