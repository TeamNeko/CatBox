package org.teamneko.schrodinger.backend.gui;

import java.util.ArrayList;

import org.teamneko.meowlib.dto.TransactionRequest.Product;

public class ProductListModel {
	ArrayList<Product> productsModified = new ArrayList<Product>();
	
	public void addOneItem(int id) {
		boolean modified = false;
		
		for(Product product : productsModified) {
			if(product.getId() == id) {
				product.setQuantity(product.getQuantity()+1);
				modified = true;
			}
		}
		
		if(!modified) {
			productsModified.add(new Product(id, 1));
		}
	}
	
	public void removeOneItem(int id) {
		boolean modified = false;
		
		for(Product product : productsModified) {
			if(product.getId() == id) {
				product.setQuantity(product.getQuantity()-1);
				modified = true;
			}
		}
		
		if(!modified) {
			productsModified.add(new Product(id, -1));
		}
	}
	
	public Product[] getProductsAdded() {
		int arraySize = 0;
		int index = 0;
		
		for(Product product : productsModified) {
			if(product.getQuantity() > 0)
				arraySize++;
		}
		
		Product[] productsAdded = new Product[arraySize];
		for(Product product : productsModified) {
			if(product.getQuantity() > 0)
				productsAdded[index++] = product;
		}
		
		return productsAdded;
	}
	
	public Product[] getProductsRemoved() {
		int arraySize = 0;
		int index = 0;
		
		for(Product product : productsModified) {
			if(product.getQuantity() < 0)
				arraySize++;
		}
		
		Product[] productsRemoved = new Product[arraySize];
		for(Product product : productsModified) {
			if(product.getQuantity() < 0) {
				product.setQuantity(0-product.getQuantity());
				productsRemoved[index++] = product;
			}
		}
		
		return productsRemoved;
	}
}
