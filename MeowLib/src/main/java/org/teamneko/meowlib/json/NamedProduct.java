package org.teamneko.meowlib.json;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class NamedProduct.
 */
@XmlRootElement
public class NamedProduct {
	
	/** The id. */
	private int id;
	
	/** The quantity. */
	private int quantity;
	
	/** The name. */
	private String name;
	
	/**
	 * Instantiates a new named product.
	 */
	public NamedProduct() {
	}
	
	/**
	 * Instantiates a new named product.
	 *
	 * @param id the id
	 * @param quantity the quantity
	 * @param name the name
	 */
	public NamedProduct(int id, int quantity, String name) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.name = name;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
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

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NamedProduct [id=" + id + ", quantity=" + quantity + ", name=" + name + "]";
	}
}
