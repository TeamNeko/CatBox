package org.teamneko.meowlib.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransactionRequest {
	private int user;
	private String box;
	private Product[] productsAdded;
	private Product[] productsRemoved;
	
	public TransactionRequest(int user, String box, Product[] productsAdded, Product[] productsRemoved) {
		this.user = user;
		this.box = box;
		this.productsAdded = productsAdded;
		this.productsRemoved = productsRemoved;
	}
	
	public TransactionRequest() {
		this.user = -1;
		this.box = "";
		this.productsAdded = new Product[0];
		this.productsRemoved = new Product[0];
	}

	public String getBox() {
		return box;
	}

	public Product[] getProductsAdded() {
		return productsAdded;
	}

	public Product[] getProductsRemoved() {
		return productsRemoved;
	}
	
	public int getUser() {
		return user;
	}
	
	public void setBox(String box) {
		this.box = box;
	}

	public void setProductsAdded(Product[] productsAdded) {
		this.productsAdded = productsAdded;
	}
	
	public void setProductsRemoved(Product[] productsRemoved) {
		this.productsRemoved = productsRemoved;
	}
	
	public void setUser(int user) {
		this.user = user;
	}
	
	public static class Product {
		private int id;
		private int quantity;
		
		public Product() {
		}
		
		public Product(int id, int quantity) {
			this.id = id;
			this.quantity = quantity;
		}

		public int getId() {
			return id;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setId(int id) {
			this.id = id;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	}
}
