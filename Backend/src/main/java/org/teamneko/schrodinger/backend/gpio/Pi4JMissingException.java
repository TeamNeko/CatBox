package org.teamneko.schrodinger.backend.gpio;


/**
 * The Class Pi4JMissingException.
 */
public class Pi4JMissingException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2342413638469372491L;

	/**
	 * Instantiates a new pi4J missing exception.
	 */
	public Pi4JMissingException() {
		super();
	}

	/**
	 * Instantiates a new pi4J missing exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 * @param enableSuppression the enable suppression
	 * @param writableStackTrace the writable stack trace
	 */
	public Pi4JMissingException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Instantiates a new pi4J missing exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public Pi4JMissingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Instantiates a new pi4J missing exception.
	 *
	 * @param message the message
	 */
	public Pi4JMissingException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new pi4J missing exception.
	 *
	 * @param cause the cause
	 */
	public Pi4JMissingException(Throwable cause) {
		super(cause);
	}
	
}
