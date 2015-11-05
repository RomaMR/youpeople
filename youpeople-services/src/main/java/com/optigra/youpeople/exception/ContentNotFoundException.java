package com.optigra.youpeople.exception;

/**
 * Created by romanmudryi on 11.08.15.
 */
public class ContentNotFoundException extends RuntimeException {

    private String value;

    public ContentNotFoundException(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
