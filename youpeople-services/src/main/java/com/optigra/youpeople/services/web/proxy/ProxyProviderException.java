package com.optigra.youpeople.services.web.proxy;

public class ProxyProviderException extends RuntimeException {

	private static final long serialVersionUID = 2917629168767563969L;
	
	public ProxyProviderException() {
		super();
	}

	public ProxyProviderException(String message) {
		super(message);
	}

	public ProxyProviderException(String message, int httpStatus) {
		super(message);
	}

	public ProxyProviderException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProxyProviderException(Throwable cause) {
		super(cause);
	}

	protected ProxyProviderException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
