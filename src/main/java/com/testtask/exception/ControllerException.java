package com.testtask.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ControllerException extends RuntimeException{
    public ControllerException(String message) {
        super(message);
    }
}
