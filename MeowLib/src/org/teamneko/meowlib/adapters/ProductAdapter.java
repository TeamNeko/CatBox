package org.teamneko.meowlib.adapters;

import java.sql.Timestamp;

import org.teamneko.meowlib.json.Product;
import org.teamneko.meowlib.sql.ProductRow;

public abstract class ProductAdapter {
	public static Product toJSONProduct(ProductRow product) {
		return new Product(product.getId(), product.getName(), product.getDescription(), product.getDate_added(), product.getDate_retired(), product.getWeight(), product.getThreshold());
	}
	
	public static ProductRow toSQLProduct(Product product) {
		return new ProductRow("", new Timestamp(product.getAdded().getTime()), new Timestamp(product.getRemoved().getTime()), product.getDescription(), product.getId(), product.getName(), product.getThreshold(), product.getWeight());
	}
}
