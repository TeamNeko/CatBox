package org.teamneko.meowlib.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Box {
	private String barcode;
	private int id;
	private float weight;
	private Date created;
	private Date modified;
	private String size;

	public Box() {
	}

	public Box(String barcode, int id, float weight, Date created, Date modified, String size) {
		this.barcode = barcode;
		this.id = id;
		this.weight = weight;
		this.created = created;
		this.modified = modified;
		this.size = size;
	}

	public String getBarcode() {
		return barcode;
	}

	public int getId() {
		return id;
	}

	public float getWeight() {
		return weight;
	}

	public Date getCreated() {
		return created;
	}

	public Date getModified() {
		return modified;
	}

	public String getSize() {
		return size;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public void setSize(String size) {
		this.size = size;
	}
}
