package com.youjiang.exception;

/**
 * Created by meng on 2017/5/3.
 */
public class UserNameCanNotBeNullException extends Exception {
    public UserNameCanNotBeNullException(String s) {
        super();
    }

    public UserNameCanNotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
