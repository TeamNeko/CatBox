package org.teamneko.meowlib.sql;

import java.sql.Timestamp;

public class ProductRow {
	private String barcode;
	private Timestamp date_added;
	private Timestamp date_retired;
	private String description;
	private int id;
	private String name;
	private int threshold;
	private double weight;
	
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

	public String getBarcode() {
		return barcode;
	}

	public Timestamp getDate_added() {
		return date_added;
	}

	public Timestamp getDate_retired() {
		return date_retired;
	}

	public String getDescription() {
		return description;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getThreshold() {
		return threshold;
	}

	public double getWeight() {
		return weight;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setDate_added(Timestamp date_added) {
		this.date_added = date_added;
	}

	public void setDate_retired(Timestamp date_retired) {
		this.date_retired = date_retired;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "ProductRow [barcode=" + barcode + ", date_added=" + date_added + ", date_retired=" + date_retired
				+ ", description=" + description + ", id=" + id + ", name=" + name + ", threshold=" + threshold
				+ ", weight=" + weight + "]";
	}
}
