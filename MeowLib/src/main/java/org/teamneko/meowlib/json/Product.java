package org.teamneko.meowlib.json;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Product.
 */
@XmlRootElement
public class Product {
	
	/** The id. */
	private int id;
	
	/** The name. */
	private String name;
	
	/** The description. */
	private String description;
	
	/** The added. */
	private Date added;
	
	/** The removed. */
	private Date removed;
	
	/** The weight. */
	private double weight;
	
	/** The threshold. */
	private int threshold;

	/**
	 * Instantiates a new product.
	 */
	public Product() {
	}

	/**
	 * Instantiates a new product.
	 *
	 * @param id the id
	 * @param name the name
	 * @param description the description
	 * @param added the added date
	 * @param removed the removed date
	 * @param weight the weight
	 * @param threshold the threshold
	 */
	public Product(int id, String name, String description, Date added, Date removed, double weight, int threshold) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.added = added;
		this.removed = removed;
		this.weight = weight;
		this.threshold = threshold;
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the added date.
	 *
	 * @return the added date
	 */
	public Date getAdded() {
		return added;
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the removed date.
	 *
	 * @return the removed date
	 */
	public Date getRemoved() {
		return removed;
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
	 * Sets the added date.
	 *
	 * @param added the new added date
	 */
	public void setAdded(Date added) {
		this.added = added;
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
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the removed date.
	 *
	 * @param removed the new removed date
	 */
	public void setRemoved(Date removed) {
		this.removed = removed;
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
	 * @param d the new weight
	 */
	public void setWeight(double d) {
		this.weight = d;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", added=" + added
				+ ", removed=" + removed + ", weight=" + weight + ", threshold=" + threshold + "]";
	}
}
