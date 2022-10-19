package com.testtask.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
