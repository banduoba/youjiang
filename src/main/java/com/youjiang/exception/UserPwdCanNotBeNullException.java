package com.youjiang.exception;


public class UserPwdCanNotBeNullException extends Exception {
    public UserPwdCanNotBeNullException(String s) {
        super(s);
    }

    public UserPwdCanNotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
