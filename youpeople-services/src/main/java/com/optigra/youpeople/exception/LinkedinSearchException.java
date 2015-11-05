package com.optigra.youpeople.exception;

/**
 * Created by romanmudryi on 25.08.15.
 */
public class LinkedinSearchException extends RuntimeException {

	private static final long serialVersionUID = 8536060178177421491L;

	public LinkedinSearchException() {
		super();
	}

	public LinkedinSearchException(String message) {
		super(message);
	}

	public LinkedinSearchException(String message, int httpStatus) {
		super(message);
	}

	public LinkedinSearchException(String message, Throwable cause) {
		super(message, cause);
	}

	public LinkedinSearchException(Throwable cause) {
		super(cause);
	}

	protected LinkedinSearchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}