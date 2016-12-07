package org.teamneko.meowlib.json;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class TransactionRequest.
 */
@XmlRootElement
public class TransactionRequest {
	
	/** The user. */
	private int user;
	
	/** The box. */
	private String box;
	
	/** The modified products. */
	private List<Product> productsModified;
	
	/**
	 * Instantiates a new transaction request.
	 *
	 * @param user the user
	 * @param box the box
	 * @param productsModified the modified products 
	 */
	public TransactionRequest(int user, String box, List<Product> productsModified) {
		this.user = user;
		this.box = box;
		this.productsModified = productsModified;
	}
	
	/**
	 * Instantiates a new transaction request.
	 */
	public TransactionRequest() {
		this.user = -1;
		this.box = "";
		this.productsModified = new ArrayList<Product>();
	}

	/**
	 * Gets the box.
	 *
	 * @return the box
	 */
	public String getBox() {
		return box;
	}
	
	/**
	 * Gets the modified products.
	 *
	 * @return the modified products
	 */
	public List<Product> getProductsModified() {
		return productsModified;
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public int getUser() {
		return user;
	}
	
	/**
	 * Sets the box.
	 *
	 * @param box the new box
	 */
	public void setBox(String box) {
		this.box = box;
	}
	
	/**
	 * Sets the modified productS.
	 *
	 * @param productsModified the new modified products
	 */
	public void setProductsModified(List<Product> productsModified) {
		this.productsModified = productsModified;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(int user) {
		this.user = user;
	}

	/**
	 * The Class Product.
	 */
	public static class Product {
		
		/** The id. */
		private int id;
		
		/** The quantity. */
		private int quantity;
		
		/**
		 * Instantiates a new product.
		 */
		public Product() {
		}
		
		/**
		 * Instantiates a new product.
		 *
		 * @param id the id
		 * @param quantity the quantity
		 */
		public Product(int id, int quantity) {
			this.id = id;
			this.quantity = quantity;
		}

		/**
		 * Gets the id.
		 *
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * Gets the quantity.
		 *
		 * @return the quantity
		 */
		public int getQuantity() {
			return quantity;
		}

		/**
		 * Sets the id.
		 *
		 * @param id the new id
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * Sets the quantity.
		 *
		 * @param quantity the new quantity
		 */
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	}
}
