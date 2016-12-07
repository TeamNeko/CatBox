package org.teamneko.meowlib.sql;

import java.sql.Timestamp;

/**
 * The Class HistoryRow.
 */
public class HistoryRow {
	
	/** The id. */
	private int id;
	
	/** The id product. */
	private int id_product;
	
	/** The quantity. */
	private int quantity;
	
	/** The time. */
	private Timestamp time;
	
	/**
	 * Instantiates a new history row.
	 */
	public HistoryRow() {
		this.id = -1;
		this.id_product = -1;
		this.quantity = -1;
		this.time = new Timestamp(0);
	}

	/**
	 * Instantiates a new history row.
	 *
	 * @param id the id
	 * @param id_product the id product
	 * @param quantity the quantity
	 * @param time the time
	 */
	public HistoryRow(int id, int id_product, int quantity, Timestamp time) {
		this.id = id;
		this.id_product = id_product;
		this.quantity = quantity;
		this.time = time;
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
	 * Gets the time.
	 *
	 * @return the time
	 */
	public Timestamp getTime() {
		return time;
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

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "HistoryItem [id=" + id + ", id_product=" + id_product + ", quantity=" + quantity + ", time=" + time
				+ "]";
	}
}
