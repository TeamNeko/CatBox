package org.teamneko.meowlib.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {
	private String barcode;
	private Product[] productsAdded;
	private Product[] productsRemoved;
	
	public Transaction(String barcode, Product[] productsAdded, Product[] productsRemoved) {
		this.barcode = barcode;
		this.productsAdded = productsAdded;
		this.productsRemoved = productsRemoved;
	}
	
	public Transaction() {
		this.barcode = "";
		this.productsAdded = new Product[0];
		this.productsRemoved = new Product[0];
	}

	public String getBarcode() {
		return barcode;
	}

	public Product[] getProductsAdded() {
		return productsAdded;
	}

	public Product[] getProductsRemoved() {
		return productsRemoved;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setProductsAdded(Product[] productsAdded) {
		this.productsAdded = productsAdded;
	}
	
	public void setProductsRemoved(Product[] productsRemoved) {
		this.productsRemoved = productsRemoved;
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
