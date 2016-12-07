package org.teamneko.schrodinger.dao;

import java.util.List;
import java.util.Optional;

import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.sql.InventoryRow;

/**
 * The Interface InventoryDAO.
 */
public interface InventoryDAO {
	
	/**
	 * Update item.
	 *
	 * @param item the item
	 */
	public void update(InventoryRow item);
	
	/**
	 * Insert item.
	 *
	 * @param item the item
	 */
	public void insert(InventoryRow item);
	
	/**
	 * Delete item.
	 *
	 * @param item the item
	 */
	public void delete(InventoryRow item);
	
	/**
	 * Gets the inventory row.
	 *
	 * @param idBox the id box
	 * @param idProduct the id product
	 * @return the inventory row
	 */
	public Optional<InventoryRow> get(int idBox, int idProduct);
	
	/**
	 * Gets the box contents.
	 *
	 * @param idBox the id box
	 * @return the box contents
	 */
	public List<NamedProduct> getBoxContents(int idBox);
	
	/**
	 * Gets the stock.
	 *
	 * @param idProduct the id product
	 * @return the stock
	 */
	public int getStock(int idProduct);
}
