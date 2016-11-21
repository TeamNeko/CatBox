package org.teamneko.schrodinger.backend.RC522;

public class Pi4JMissingException extends Exception {
	private static final long serialVersionUID = 2342413638469372491L;

	public Pi4JMissingException() {
		super();
	}

	public Pi4JMissingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public Pi4JMissingException(String message, Throwable cause) {
		super(message, cause);
	}

	public Pi4JMissingException(String message) {
		super(message);
	}

	public Pi4JMissingException(Throwable cause) {
		super(cause);
	}
	
}
