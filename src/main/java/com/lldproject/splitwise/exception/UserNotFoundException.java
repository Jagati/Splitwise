package com.lldproject.splitwise.exception;

import lombok.experimental.StandardException;

@StandardException
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
