package org.teamneko.meowlib.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CompositeSearchResult extends SearchResult {
	private Box box;
	private Product product;
	private User user;

	public CompositeSearchResult() {
	}
	
	public CompositeSearchResult(String type, Box box, Product product, User user) {
		super(type);
		this.box = box;
		this.product = product;
		this.user = user;
	}

	public Box getBox() {
		return box;
	}

	public Product getProduct() {
		return product;
	}

	public User getUser() {
		return user;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
