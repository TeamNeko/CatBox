package org.teamneko.meowlib.json;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class CompositeSearchResult.
 */
@XmlRootElement
public class CompositeSearchResult extends SearchResult {
	
	/** The box. */
	private Box box;
	
	/** The product. */
	private Product product;
	
	/** The user. */
	private User user;

	/**
	 * Instantiates a new composite search result.
	 */
	public CompositeSearchResult() {
	}
	
	/**
	 * Instantiates a new composite search result.
	 *
	 * @param type the type
	 * @param box the box
	 * @param product the product
	 * @param user the user
	 */
	public CompositeSearchResult(String type, Box box, Product product, User user) {
		super(type);
		this.box = box;
		this.product = product;
		this.user = user;
	}

	/**
	 * Gets the box.
	 *
	 * @return the box
	 */
	public Box getBox() {
		return box;
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the box.
	 *
	 * @param box the new box
	 */
	public void setBox(Box box) {
		this.box = box;
	}

	/**
	 * Sets the product.
	 *
	 * @param product the new product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
