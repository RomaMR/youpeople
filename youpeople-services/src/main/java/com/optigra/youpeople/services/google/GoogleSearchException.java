package com.optigra.youpeople.services.google;

public class GoogleSearchException extends RuntimeException {

	private static final long serialVersionUID = -6857386771553730125L;
	
    public GoogleSearchException() {
        super();
    }

    public GoogleSearchException(String message) {
        super(message);
    }

    public GoogleSearchException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoogleSearchException(Throwable cause) {
        super(cause);
    }

    protected GoogleSearchException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
