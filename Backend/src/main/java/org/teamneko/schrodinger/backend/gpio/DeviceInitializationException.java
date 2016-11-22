package org.teamneko.schrodinger.backend.gpio;

public class DeviceInitializationException extends Exception {
	private static final long serialVersionUID = -1465790667080873043L;

	public DeviceInitializationException() {
		super();
	}

	public DeviceInitializationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DeviceInitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DeviceInitializationException(String message) {
		super(message);
	}

	public DeviceInitializationException(Throwable cause) {
		super(cause);
	}
}
