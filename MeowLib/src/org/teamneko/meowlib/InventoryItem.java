package org.teamneko.meowlib;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class InventoryItem {
	private int idBox;
	private int idProduct;
	private int quantity;
	
	public InventoryItem() {
	}
	
	public InventoryItem(int idBox, int idProduct, int quantity) {
		this.idBox = idBox;
		this.idProduct = idProduct;
		this.quantity = quantity;
	}

	public int getIdBox() {
		return idBox;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setIdBox(int idBox) {
		this.idBox = idBox;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	


}
