package com.youjiang.exception;


public class UserAireadyExistException extends Exception {
    public UserAireadyExistException(String s) {
        super(s);
    }

    public UserAireadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
