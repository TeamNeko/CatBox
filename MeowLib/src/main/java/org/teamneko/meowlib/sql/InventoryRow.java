package org.teamneko.meowlib.sql;

/**
 * The Class InventoryRow.
 */
public class InventoryRow {
	
	/** The id. */
	private int id;
	
	/** The id box. */
	private int id_box;
	
	/** The id product. */
	private int id_product;
	
	/** The quantity. */
	private int quantity;
	
	/**
	 * Instantiates a new inventory row.
	 */
	public InventoryRow() {
	}
	
	/**
	 * Instantiates a new inventory row.
	 *
	 * @param id the id
	 * @param id_box the id box
	 * @param id_product the id product
	 * @param quantity the quantity
	 */
	public InventoryRow(int id, int id_box, int id_product, int quantity) {
		this.id = id;
		this.id_box = id_box;
		this.id_product = id_product;
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
	 * Gets the id box.
	 *
	 * @return the id box
	 */
	public int getId_box() {
		return id_box;
	}

	/**
	 * Gets the id product.
	 *
	 * @return the id product
	 */
	public int getId_product() {
		return id_product;
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
	 * Sets the id box.
	 *
	 * @param id_box the new id box
	 */
	public void setId_box(int id_box) {
		this.id_box = id_box;
	}

	/**
	 * Sets the id product.
	 *
	 * @param id_product the new id product
	 */
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InventoryItem [id=" + id + ", id_box=" + id_box + ", id_product=" + id_product + ", quantity="
				+ quantity + "]";
	}
	
	
}
