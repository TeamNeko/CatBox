package org.teamneko.meowlib.sql;

import java.sql.Timestamp;

/**
 * The Class ProductRow.
 */
public class ProductRow {
	
	/** The barcode. */
	private String barcode;
	
	/** The date added. */
	private Timestamp date_added;
	
	/** The date retired. */
	private Timestamp date_retired;
	
	/** The description. */
	private String description;
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The threshold. */
	private int threshold;
	
	/** The weight. */
	private double weight;
	
	/**
	 * Instantiates a new product row.
	 */
	public ProductRow() {
		this.barcode = "";
		this.date_added = new Timestamp(0);
		this.date_retired = new Timestamp(0);
		this.description = "";
		this.id = -1;
		this.name = "";
		this.threshold = -1;
		this.weight = Double.NaN;
	}

	/**
	 * Instantiates a new product row.
	 *
	 * @param barcode the barcode
	 * @param date_added the date added
	 * @param date_retired the date retired
	 * @param description the description
	 * @param id the id
	 * @param name the name
	 * @param threshold the threshold
	 * @param weight the weight
	 */
	public ProductRow(String barcode, Timestamp date_added, Timestamp date_retired, String description, int id, String name,
			int threshold, double weight) {
		this.barcode = barcode;
		this.date_added = date_added;
		this.date_retired = date_retired;
		this.description = description;
		this.id = id;
		this.name = name;
		this.threshold = threshold;
		this.weight = weight;
	}

	/**
	 * Gets the barcode.
	 *
	 * @return the barcode
	 */
	public String getBarcode() {
		return barcode;
	}

	/**
	 * Gets the date added.
	 *
	 * @return the date added
	 */
	public Timestamp getDate_added() {
		return date_added;
	}

	/**
	 * Gets the date retired.
	 *
	 * @return the date retired
	 */
	public Timestamp getDate_retired() {
		return date_retired;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the threshold.
	 *
	 * @return the threshold
	 */
	public int getThreshold() {
		return threshold;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Sets the barcode.
	 *
	 * @param barcode the new barcode
	 */
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * Sets the date added.
	 *
	 * @param date_added the new date added
	 */
	public void setDate_added(Timestamp date_added) {
		this.date_added = date_added;
	}

	/**
	 * Sets the date retired.
	 *
	 * @param date_retired the new date retired
	 */
	public void setDate_retired(Timestamp date_retired) {
		this.date_retired = date_retired;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the threshold.
	 *
	 * @param threshold the new threshold
	 */
	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductRow [barcode=" + barcode + ", date_added=" + date_added + ", date_retired=" + date_retired
				+ ", description=" + description + ", id=" + id + ", name=" + name + ", threshold=" + threshold
				+ ", weight=" + weight + "]";
	}
}
