package org.teamneko.schrodinger.dao;

import java.util.List;
import java.util.Optional;

import org.teamneko.meowlib.sql.ProductRow;

public interface ProductsDAO {
	public Optional<ProductRow> get(int id);
	public List<ProductRow> getProducts();
	public Optional<ProductRow> search(String barcode);
}
