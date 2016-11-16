package org.teamneko.meowlib.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TransactionRequest {
	private int user;
	private String box;
	private List<Product> productsModified;
	
	public TransactionRequest(int user, String box, List<Product> productsModified) {
		this.user = user;
		this.box = box;
		this.productsModified = productsModified;
	}
	
	public TransactionRequest() {
		this.user = -1;
		this.box = "";
		this.productsModified = new ArrayList<Product>();
	}

	public String getBox() {
		return box;
	}
	
	public List<Product> getProductsModified() {
		return productsModified;
	}
	
	public int getUser() {
		return user;
	}
	
	public void setBox(String box) {
		this.box = box;
	}
	
	public void setProductsModified(List<Product> productsModified) {
		this.productsModified = productsModified;
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
