package org.teamneko.meowlib.json;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Product {
	private int id;
	private String name;
	private String description;
	private Date added;
	private Date removed;
	private double weight;
	private int threshold;

	public Product() {
	}

	public Product(int id, String name, String description, Date added, Date removed, double weight, int threshold) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.added = added;
		this.removed = removed;
		this.weight = weight;
		this.threshold = threshold;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAdded() {
		return added;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Date getRemoved() {
		return removed;
	}

	public int getThreshold() {
		return threshold;
	}
	
	public double getWeight() {
		return weight;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRemoved(Date removed) {
		this.removed = removed;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}
	
	public void setWeight(double d) {
		this.weight = d;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", added=" + added
				+ ", removed=" + removed + ", weight=" + weight + ", threshold=" + threshold + "]";
	}
}