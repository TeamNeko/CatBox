package org.teamneko.meowlib;

public class InventoryItem {
	private int id;
	private int id_box;
	private int id_product;
	private int quantity;
	
	public InventoryItem() {
	}
	
	public InventoryItem(int id, int idBox, int idProduct, int quantity) {
		this.id = id;
		this.id_box = idBox;
		this.id_box = idProduct;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public int getId_box() {
		return id_box;
	}

	public int getId_product() {
		return id_product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId_box(int id_box) {
		this.id_box = id_box;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", id_box=" + id_box + ", id_product=" + id_product + ", quantity="
				+ quantity + "]";
	}
	
	
}
