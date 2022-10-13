package com.testtask.eception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RepositoryException extends RuntimeException{
    public RepositoryException(String message) {
        super(message);
    }
}
