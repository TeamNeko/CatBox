package org.teamneko.schrodinger.backend.gpio;


/**
 * The Enum Duration.
 */
public enum Duration {
	
	// A 1 represent an eighth-note, a 2 represent a quarter-note, a 4 represent an half-note
	Eight(1),
	Quarter(2),
	Half(4);
	
	/** The value. */
	public final int value;
	
	/**
	 * Instantiates a new duration.
	 *
	 * @param value the value
	 */
	private Duration(int value) {
		this.value = value;
	}
}
