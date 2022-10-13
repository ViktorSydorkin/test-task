package com.testtask.eception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }
}
