package org.teamneko.schrodinger.dao;

import java.util.Optional;

import org.teamneko.meowlib.json.Box;

/**
 * The Interface BoxesDAO.
 */
public interface BoxesDAO {
	
	/**
	 * Check if barcode exist.
	 *
	 * @param barcode the barcode
	 * @return true, if successful
	 */
	public boolean exists(String barcode);
	
	/**
	 * Creates the barcode.
	 *
	 * @param barcode the barcode
	 */
	public void create(String barcode);
	
	/**
	 * Gets the id.
	 *
	 * @param barcode the barcode
	 * @return the id
	 */
	public int getId(String barcode);
	
	/**
	 * Search for the barcode.
	 *
	 * @param barcode the barcode
	 * @return the optional
	 */
	public Optional<Box> search(String barcode);
}
