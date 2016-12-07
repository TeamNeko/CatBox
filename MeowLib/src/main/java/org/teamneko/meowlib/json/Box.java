package org.teamneko.meowlib.json;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Box.
 */
@XmlRootElement
public class Box {
	
	/** The barcode. */
	private String barcode;
	
	/** The id. */
	private int id;
	
	/** The weight. */
	private float weight;
	
	/** The creation date. */
	private Date created;
	
	/** The modified. */
	private Date modified;
	
	/** The size. */
	private String size;

	/**
	 * Instantiates a new box.
	 */
	public Box() {
	}

	/**
	 * Instantiates a new box.
	 *
	 * @param barcode the barcode
	 * @param id the id
	 * @param weight the weight
	 * @param created the creation date
	 * @param modified the modified date
	 * @param size the size
	 */
	public Box(String barcode, int id, float weight, Date created, Date modified, String size) {
		this.barcode = barcode;
		this.id = id;
		this.weight = weight;
		this.created = created;
		this.modified = modified;
		this.size = size;
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
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the weight.
	 *
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}

	/**
	 * Gets the creation date.
	 *
	 * @return the creation date
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * Gets the modified date.
	 *
	 * @return the modified date
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public String getSize() {
		return size;
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
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Sets the weight.
	 *
	 * @param weight the new weight
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}

	/**
	 * Sets the creation date.
	 *
	 * @param created the new creation date
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Box [barcode=" + barcode + ", id=" + id + ", weight=" + weight + ", created=" + created + ", modified="
				+ modified + ", size=" + size + "]";
	}

	/**
	 * Sets the modified date.
	 *
	 * @param modified the new modified date
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * Sets the size.
	 *
	 * @param size the new size
	 */
	public void setSize(String size) {
		this.size = size;
	}
}
