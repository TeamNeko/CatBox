package org.teamneko.schrodinger.dao;

import java.util.List;
import java.util.Optional;

import org.teamneko.meowlib.sql.ProductRow;

/**
 * The Interface ProductsDAO.
 */
public interface ProductsDAO {
	
	/**
	 * Gets the product row.
	 *
	 * @param id the id
	 * @return the product row
	 */
	public Optional<ProductRow> get(int id);
	
	/**
	 * Gets the products.
	 *
	 * @return the products
	 */
	public List<ProductRow> getProducts();
	
	/**
	 * Search the product row with the barcode.
	 *
	 * @param barcode the barcode
	 * @return the product row
	 */
	public Optional<ProductRow> search(String barcode);
}
