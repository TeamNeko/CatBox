package org.teamneko.meowlib;

import java.util.Date;

public class Alert {
	private int id;
	private int id_product;
	private int id_message;
	private Date time;
	
	public Alert() {
		this.id = -1;
		this.id_product = -1;
		this.id_message = -1;
		this.time = new Date();
	}

	public Alert(int id, int id_product, int id_message, Date time) {
		this.id = id;
		this.id_product = id_product;
		this.id_message = id_message;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public int getId_product() {
		return id_product;
	}

	public int getId_message() {
		return id_message;
	}

	public Date getTime() {
		return time;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setId_product(int id_product) {
		this.id_product = id_product;
	}

	public void setId_message(int id_message) {
		this.id_message = id_message;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Alert [id=" + id + ", id_product=" + id_product + ", id_message=" + id_message + ", time=" + time + "]";
	}
}
