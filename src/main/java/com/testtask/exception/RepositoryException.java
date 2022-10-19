package com.testtask.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RepositoryException extends RuntimeException {
  public RepositoryException(String message, Throwable cause) {
    super(message, cause);
  }
}
