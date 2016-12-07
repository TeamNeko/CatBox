package org.teamneko.meowlib.adapters;

import java.sql.Timestamp;

import org.teamneko.meowlib.json.Product;
import org.teamneko.meowlib.sql.ProductRow;

/**
 * The Class ProductAdapter.
 */
public abstract class ProductAdapter {
	
	/**
	 * To JSON product.
	 *
	 * @param product the product
	 * @return the product
	 */
	public static Product toJSONProduct(ProductRow product) {
		return new Product(product.getId(), product.getName(), product.getDescription(), product.getDate_added(), product.getDate_retired(), product.getWeight(), product.getThreshold());
	}
	
	/**
	 * To SQL product.
	 *
	 * @param product the product
	 * @return the product row
	 */
	public static ProductRow toSQLProduct(Product product) {
		return new ProductRow("", new Timestamp(product.getAdded().getTime()), new Timestamp(product.getRemoved().getTime()), product.getDescription(), product.getId(), product.getName(), product.getThreshold(), product.getWeight());
	}
}
