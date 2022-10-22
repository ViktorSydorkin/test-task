package com.testtask.repository.impl;

import com.testtask.entity.User;
import com.testtask.exception.EntityAlreadyExistsException;
import com.testtask.exception.NoSuchEntityException;
import com.testtask.repository.inter.UserRepo;
import com.testtask.repository.util.SQL;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class UserRepoImpl implements UserRepo {
  private final NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public User findByEmail(String email) {
    SqlParameterSource params = new MapSqlParameterSource().addValue("email", email);
    try {
      return jdbcTemplate.queryForObject(
          SQL.GET_USER_BY_EMAIL, params, new BeanPropertyRowMapper<>(User.class));
    } catch (DataAccessException dataAccessException) {
      throw new NoSuchEntityException();
    }
  }

  @Override
  public User create(User user) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    SqlParameterSource params =
        new MapSqlParameterSource()
            .addValue("username", user.getUsername())
            .addValue("email", user.getEmail())
            .addValue("password", user.getPassword())
            .addValue("enabled", user.isEnabled());
    try {
      jdbcTemplate.update(SQL.ADD_USER, params, keyHolder);
      user.setUserId(Objects.requireNonNull(keyHolder.getKey()).longValue());
      return user;
    } catch (DataAccessException dataAccessException) {
      throw new EntityAlreadyExistsException("Entity with such email already exists");
    }
  }
}
