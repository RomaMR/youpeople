package com.optigra.youpeople.exception;

/**
 * Created by romanmudryi on 14.10.15.
 */
public class UserAlreadyExistsException extends RuntimeException {

    private String value;

    public UserAlreadyExistsException(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}