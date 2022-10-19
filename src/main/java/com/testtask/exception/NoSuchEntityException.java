package com.testtask.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NoSuchEntityException extends RuntimeException{
    public NoSuchEntityException(String message) {
        super(message);
    }
}
