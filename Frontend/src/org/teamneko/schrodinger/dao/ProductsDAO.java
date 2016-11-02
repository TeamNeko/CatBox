package org.teamneko.schrodinger.dao;

import java.util.List;
import java.util.Optional;

import org.teamneko.meowlib.dto.Product;

public interface ProductsDAO {
	public List<Product> getProducts();
	public Optional<Product> search(String barcode);
}
