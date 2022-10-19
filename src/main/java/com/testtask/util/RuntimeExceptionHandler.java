package com.testtask.util;

import com.testtask.exception.EntityAlreadyExistsException;
import com.testtask.exception.NoSuchEntityException;
import com.testtask.exception.RepositoryException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class RuntimeExceptionHandler {
  @ExceptionHandler(NoSuchEntityException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String noSuchEntityExceptionHandler(NoSuchEntityException noSuchEntityException) {
    log.error(
        "Exception was caught {} {}", noSuchEntityException, noSuchEntityException.getMessage());
    return noSuchEntityException.getMessage();
  }

  @ExceptionHandler(EntityAlreadyExistsException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String entityAlreadyExistsExceptionHandler(
      EntityAlreadyExistsException entityAlreadyExistsException) {
    log.error(
        "Exception was caught {} {}",
        entityAlreadyExistsException,
        entityAlreadyExistsException.getMessage());
    return entityAlreadyExistsException.getMessage();
  }

  @ExceptionHandler(RepositoryException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String repositoryException(RepositoryException repositoryException) {
    log.error(
        "Exception was caught {} {} {} {}",
        repositoryException,
        repositoryException.getMessage(),
        repositoryException.getCause(),
        repositoryException.getCause().getMessage());
    return repositoryException.getMessage() +". " + repositoryException.getCause().getMessage() ;
  }
}
