package org.teamneko.schrodinger.dao;

import java.util.List;

import org.teamneko.meowlib.sql.HistoryRow;

/**
 * The Interface HistoryDAO.
 */
public interface HistoryDAO {
	
	/**
	 * Adds the item.
	 *
	 * @param item the item
	 */
	public void add(HistoryRow item);
	
	/**
	 * Gets the full history.
	 *
	 * @param idProduct the id product
	 * @return the full history
	 */
	public List<HistoryRow> getFullHistory(int idProduct);
}
