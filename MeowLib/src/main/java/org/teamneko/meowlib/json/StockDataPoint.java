package org.teamneko.meowlib.json;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StockDataPoint {
	private Date time;
	private int quantity;
	
	public StockDataPoint() {
		time = new Date();
		quantity = -1;
	}

	public StockDataPoint(Date time, int quantity) {
		this.time = time;
		this.quantity = quantity;
	}

	public Date getTime() {
		return time;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "StockDataPoint [time=" + time + ", quantity=" + quantity + "]";
	}
}
