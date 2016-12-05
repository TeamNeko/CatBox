package org.teamneko.meowlib.sql;

import java.sql.Timestamp;

/**
 * The Class AlertRow.
 */
public class AlertRow {
	
	/** The id. */
	private int id;
	
	/** The id product. */
	private int id_product;
	
	/** The id message. */
	private int id_message;
	
	/** The time. */
	private Timestamp time;
	
	/**
	 * Instantiates a new alert row.
	 */
	public AlertRow() {
		this.id = -1;
		this.id_product = -1;
		this.id_message = -1;
		this.time = new Timestamp(0);
	}

	/**
	 * Instantiates a new alert row.
	 *
	 * @param id the id
	 * @param id_product the id product
	 * @param id_message the id message
	 * @param time the time
	 */
	public AlertRow(int id, int id_product, int id_message, Timestamp time) {
		this.id = id;
		this.id_product = id_product;
		this.id_message = id_message;
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
	 * Gets the id message.
	 *
	 * @return the id message
	 */
	public int getId_message() {
		return id_message;
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
	 * Sets the id message.
	 *
	 * @param id_message the new id message
	 */
	public void setId_message(int id_message) {
		this.id_message = id_message;
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
		return "Alert [id=" + id + ", id_product=" + id_product + ", id_message=" + id_message + ", time=" + time + "]";
	}
}
