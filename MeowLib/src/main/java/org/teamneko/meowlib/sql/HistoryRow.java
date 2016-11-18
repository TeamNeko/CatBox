package org.teamneko.meowlib.sql;

import java.sql.Timestamp;

public class HistoryRow {
	private int id;
	private int id_product;
	private int quantity;
	private Timestamp time;
	
	public HistoryRow() {
		this.id = -1;
		this.id_product = -1;
		this.quantity = -1;
		this.time = new Timestamp(0);
	}

	public HistoryRow(int id, int id_product, int quantity, Timestamp time) {
		this.id = id;
		this.id_product = id_product;
		this.quantity = quantity;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public int getId_product() {
		return id_product;
	}

	public int getQuantity() {
		return quantity;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "HistoryItem [id=" + id + ", id_product=" + id_product + ", quantity=" + quantity + ", time=" + time
				+ "]";
	}
}
