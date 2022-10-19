package com.testtask.repository.impl;

import com.testtask.entity.Department;
import com.testtask.exception.EntityAlreadyExistsException;
import com.testtask.exception.NoSuchEntityException;
import com.testtask.exception.RepositoryException;
import com.testtask.repository.inter.DepartmentRepo;
import com.testtask.repository.util.SQL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
@RequiredArgsConstructor
public class DepartmentRepoImpl implements DepartmentRepo {
  private final NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public Department getDepartmentById(long id) {
    SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
    try {
      return jdbcTemplate.queryForObject(
          SQL.GET_DEPARTMENT_BY_ID, params, new BeanPropertyRowMapper<>(Department.class));
    } catch (DataAccessException dataAccessException) {
      throw new RepositoryException("SQL query went wrong", dataAccessException);
    }
  }

  @Override
  public List<Department> getAllDepartments() {
    try {
      return jdbcTemplate.query(
          SQL.GET_ALL_DEPARTMENTS, new BeanPropertyRowMapper<>(Department.class));
    } catch (DataAccessException dataAccessException) {
      throw new RepositoryException("SQL query went wrong", dataAccessException);
    }
  }

  @Override
  public Department updateDepartment(Department department) {
    SqlParameterSource params =
        new MapSqlParameterSource()
            .addValue("id", department.getDepartmentId())
            .addValue("name", department.getDepartmentName());
    if (jdbcTemplate.update(SQL.UPDATE_DEPARTMENT, params) == 0)
      throw new NoSuchEntityException(
          "The department with id " + department.getDepartmentId() + " doesn't exists");
    return department;
  }

  @Override
  public Department addDepartment(Department department) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    SqlParameterSource params =
        new MapSqlParameterSource().addValue("name", department.getDepartmentName());
    if (jdbcTemplate.update(SQL.ADD_DEPARTMENT, params, keyHolder, new String[] {"Id"}) == 0)
      throw new EntityAlreadyExistsException(
          "The department with id " + department.getDepartmentId() + " already exists");
    department.setDepartmentId(Objects.requireNonNull(keyHolder.getKey()).longValue());
    return department;
  }

  @Override
  public void deleteDepartment(long id) {
    SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
    if (jdbcTemplate.update(SQL.DELETE_DEPARTMENT, params) == 0)
      throw new NoSuchEntityException("The department with id " + id + " doesn't exists");
  }
}
