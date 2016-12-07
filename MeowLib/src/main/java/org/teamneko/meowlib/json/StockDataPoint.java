package org.teamneko.meowlib.json;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class StockDataPoint.
 */
@XmlRootElement
public class StockDataPoint {
	
	/** The time. */
	private Date time;
	
	/** The quantity. */
	private int quantity;
	
	/**
	 * Instantiates a new stock data point.
	 */
	public StockDataPoint() {
		time = new Date();
		quantity = -1;
	}

	/**
	 * Instantiates a new stock data point.
	 *
	 * @param time the time
	 * @param quantity the quantity
	 */
	public StockDataPoint(Date time, int quantity) {
		this.time = time;
		this.quantity = quantity;
	}

	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StockDataPoint [time=" + time + ", quantity=" + quantity + "]";
	}
}
