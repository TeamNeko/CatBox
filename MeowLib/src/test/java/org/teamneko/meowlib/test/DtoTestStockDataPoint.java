package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.json.StockDataPoint;

public class DtoTestStockDataPoint {

	StockDataPoint testStockDataPoint;
	private Date time;
	
	@Before
	public void initiateTestStockDataPoint() {
		time = new Date();
		testStockDataPoint = new StockDataPoint();
	}
	
	@Test
	public void getSetStockDataPointTest() {
		testStockDataPoint.setQuantity(1);
		testStockDataPoint.setTime(time);
		assertEquals(1,testStockDataPoint.getQuantity());
		assertEquals(time,testStockDataPoint.getTime());
	}
	
	@Test
	public void toStringStockDataPointTest() {
		testStockDataPoint = new StockDataPoint(time, 50);
		assertEquals("StockDataPoint [time=" + time + ", quantity=50]",testStockDataPoint.toString());
	}

}
