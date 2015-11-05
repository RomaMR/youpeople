package com.optigra.youpeople.exception;

/**
 * Created by romanmudryi on 03.10.15.
 */
public class PersonUploadException extends RuntimeException {

    private String value;

    public PersonUploadException(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}