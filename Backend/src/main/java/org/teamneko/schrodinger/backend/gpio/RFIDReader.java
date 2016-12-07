package org.teamneko.schrodinger.backend.gpio;


/**
 * The Interface RFIDReader.
 */
public interface RFIDReader {
	
	/**
	 * Read.
	 *
	 * @return true, if successful
	 */
	public boolean read();
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getID();
}
