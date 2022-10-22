package com.testtask.controller;

import com.testtask.exception.ApiError;
import com.testtask.exception.EntityAlreadyExistsException;
import com.testtask.exception.NoSuchEntityException;
import com.testtask.exception.RepositoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ApplicationExceptionHandler {
  @ExceptionHandler(NoSuchEntityException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError noSuchEntityExceptionHandler(NoSuchEntityException noSuchEntityException) {
    log.error(
        "Exception was caught {} {}", noSuchEntityException, noSuchEntityException.getMessage());
    return new ApiError(
        HttpStatus.BAD_REQUEST, noSuchEntityException.getMessage(), LocalDateTime.now());
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError entityAlreadyExistsExceptionHandler(
      EntityAlreadyExistsException entityAlreadyExistsException) {
    log.error(
        "Exception was caught {} {}",
        entityAlreadyExistsException,
        entityAlreadyExistsException.getMessage());
    return new ApiError(
        HttpStatus.BAD_REQUEST, entityAlreadyExistsException.getMessage(), LocalDateTime.now());
  }

  @ExceptionHandler(RepositoryException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ApiError repositoryException(RepositoryException repositoryException) {
    log.error(
        "Exception was caught {} {} {} {}",
        repositoryException,
        repositoryException.getMessage(),
        repositoryException.getCause(),
        repositoryException.getCause().getMessage());
    return new ApiError(
        HttpStatus.BAD_REQUEST,
        repositoryException.getMessage() + ". " + repositoryException.getCause().getMessage(),
        LocalDateTime.now());
  }
}
