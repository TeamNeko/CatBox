package org.teamneko.schrodinger.backend.gpio;


/**
 * The Class DeviceInitializationException.
 */
public class DeviceInitializationException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1465790667080873043L;

	/**
	 * Instantiates a new device initialization exception.
	 */
	public DeviceInitializationException() {
		super();
	}

	/**
	 * Instantiates a new device initialization exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public DeviceInitializationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new device initialization exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public DeviceInitializationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new device initialization exception.
	 *
	 * @param message the message
	 */
	public DeviceInitializationException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new device initialization exception.
	 *
	 * @param cause the cause
	 */
	public DeviceInitializationException(Throwable cause) {
		super(cause);
	}
}
