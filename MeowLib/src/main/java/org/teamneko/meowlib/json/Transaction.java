package org.teamneko.meowlib.json;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Transaction.
 */
@XmlRootElement
public class Transaction {
	
	/** The id user. */
	private int idUser;
	
	/** The id product. */
	private int idProduct;
	
	/** The id box. */
	private int idBox;
	
	/** The quantity. */
	private int quantity;
	
	/** The time. */
	private long time;
	
	/** The type. */
	private String type;
	
	/**
	 * Instantiates a new transaction.
	 */
	public Transaction() {
		this.idUser = -1;
		this.idProduct = -1;
		this.idBox = -1;
		this.quantity = -1;
		this.time = -1;
		this.type = "";
	}
	
	/**
	 * Instantiates a new transaction.
	 *
	 * @param idUser the id user
	 * @param idProduct the id product
	 * @param idBox the id box
	 * @param quantity the quantity
	 * @param time the time
	 * @param type the type
	 */
	public Transaction(int idUser, int idProduct, int idBox, int quantity, long time, String type) {
		this.idUser = idUser;
		this.idProduct = idProduct;
		this.idBox = idBox;
		this.quantity = quantity;
		this.time = time;
		this.type = type;
	}
	
	/**
	 * Gets the id user.
	 *
	 * @return the id user
	 */
	public int getIdUser() {
		return idUser;
	}
	
	/**
	 * Gets the id product.
	 *
	 * @return the id product
	 */
	public int getIdProduct() {
		return idProduct;
	}
	
	/**
	 * Gets the id box.
	 *
	 * @return the id box
	 */
	public int getIdBox() {
		return idBox;
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
	 * Gets the time.
	 *
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the id user.
	 *
	 * @param idUser the new id user
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	/**
	 * Sets the id product.
	 *
	 * @param idProduct the new id product
	 */
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	
	/**
	 * Sets the id box.
	 *
	 * @param idBox the new id box
	 */
	public void setIdBox(int idBox) {
		this.idBox = idBox;
	}
	
	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
}
