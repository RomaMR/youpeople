package com.optigra.youpeople.services.facebook.stat;

public class FacebookInsightException extends RuntimeException {

	private static final long serialVersionUID = -7981603962780520923L;

	public FacebookInsightException() {
        super();
    }

    public FacebookInsightException(String message) {
        super(message);
    }

    public FacebookInsightException(String message, Throwable cause) {
        super(message, cause);
    }

    public FacebookInsightException(Throwable cause) {
        super(cause);
    }

    protected FacebookInsightException(String message, Throwable cause,
                               boolean enableSuppression,
                               boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
