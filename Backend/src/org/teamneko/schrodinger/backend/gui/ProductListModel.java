package org.teamneko.schrodinger.backend.gui;

import java.util.ArrayList;

import org.teamneko.meowlib.dto.TransactionRequest.Product;

public class ProductListModel {
	ArrayList<Product> productsModified = new ArrayList<Product>();
	
	public void addOneItem(int id) {
		for(Product product : productsModified) {
			if(product.getId() == id)
				product.setQuantity(product.getQuantity()+1);
		}
	}
	
	public void removeOneItem(int id) {
		for(Product product : productsModified) {
			if(product.getId() == id)
				product.setQuantity(product.getQuantity()-1);
		}
	}
	
	public Product[] getProductsAdded() {
		ArrayList<Product> productsAdded = new ArrayList<Product>();
		for(Product product : productsModified) {
			if(product.getQuantity() > 0)
				productsAdded.add(product);
		}
		
		return (Product[]) productsAdded.toArray();
	}
	
	public Product[] getProductsRemoved() {
		ArrayList<Product> productsRemoved = new ArrayList<Product>();
		for(Product product : productsModified) {
			if(product.getQuantity() < 0)
				product.setQuantity(0-product.getQuantity());
				productsRemoved.add(product);
		}
		
		return (Product[]) productsRemoved.toArray();
	}
}
