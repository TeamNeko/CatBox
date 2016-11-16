package org.teamneko.meowlib.json;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NamedProduct {
	private int id;
	private int quantity;
	private String name;
	
	public NamedProduct() {
	}
	
	public NamedProduct(int id, int quantity, String name) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getName() {
		return name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NamedProduct [id=" + id + ", quantity=" + quantity + ", name=" + name + "]";
	}
}
