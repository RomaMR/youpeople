package com.optigra.youpeople.services.web;

public class WebException extends RuntimeException {

	private static final long serialVersionUID = 2917629168767563969L;
	
	private final int httpStatus;

	public WebException() {
		super();
		httpStatus = 0;
	}

	public WebException(String message) {
		super(message);
		httpStatus = 0;
	}

	public WebException(String message, int httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	public WebException(String message, Throwable cause) {
		super(message, cause);
		httpStatus = 0;
	}

	public WebException(Throwable cause) {
		super(cause);
		httpStatus = 0;
	}

	protected WebException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		httpStatus = 0;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

}
