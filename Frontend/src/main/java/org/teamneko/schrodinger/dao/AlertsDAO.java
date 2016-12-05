package org.teamneko.schrodinger.dao;

import java.util.Optional;

import org.teamneko.meowlib.sql.AlertRow;


/**
 * The Interface AlertsDAO.
 */
public interface AlertsDAO {
	
	/**
	 * Gets the alert.
	 *
	 * @param idProduct the id product
	 * @return the alert
	 */
	public Optional<AlertRow> getAlert(int idProduct);
	
	/**
	 * Change level.
	 *
	 * @param idProduct the id product
	 * @param level the level
	 */
	public void changeLevel(int idProduct, int level);
	
	/**
	 * Adds the alert.
	 *
	 * @param idProduct the id product
	 * @param level the level
	 */
	public void addAlert(int idProduct, int level);
	
	/**
	 * Removes the alert.
	 *
	 * @param idProduct the id product
	 */
	public void removeAlert(int idProduct);
}
