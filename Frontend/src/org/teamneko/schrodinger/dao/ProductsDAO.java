package org.teamneko.schrodinger.dao;

import java.util.List;
import java.util.Optional;

import org.teamneko.meowlib.Product;

public interface ProductsDAO {
	public Optional<Product> get(int id);
	public List<Product> getProducts();
	public Optional<Product> search(String barcode);
}
