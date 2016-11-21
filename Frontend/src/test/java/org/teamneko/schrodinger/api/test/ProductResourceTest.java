package org.teamneko.schrodinger.api.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.teamneko.meowlib.json.Product;
import org.teamneko.meowlib.json.StockDataPoint;
import org.teamneko.meowlib.sql.HistoryRow;
import org.teamneko.meowlib.sql.ProductRow;
import org.teamneko.schrodinger.api.BoxResource;
import org.teamneko.schrodinger.api.ProductResource;
import org.teamneko.schrodinger.dao.HistoryDAO;
import org.teamneko.schrodinger.dao.InventoryDAO;
import org.teamneko.schrodinger.dao.ProductsDAO;

import com.sun.jersey.api.NotFoundException;

public class ProductResourceTest {
//public List<StockDataPoint> getBoxDetails(@PathParam("id") int id) {
	private ProductResource resourceUnderTest = new ProductResource();
	
	private HistoryDAO historyMock;
	private ProductsDAO productsMock;
	
	public ProductResourceTest() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		historyMock = mock(HistoryDAO.class);
		productsMock = mock(ProductsDAO.class);
		
		Calendar c = Calendar.getInstance();
		List<HistoryRow> list = new ArrayList<HistoryRow>();
		
		c.set(2016, 11, 20, 14, 20);
		list.add(new HistoryRow(1, 1, 5, new Timestamp(c.getTimeInMillis())));
		
		c.set(2016, 11, 30, 15, 40);
		list.add(new HistoryRow(1, 1, 3, new Timestamp(c.getTimeInMillis())));
		
		when(productsMock.get(1)).thenReturn(Optional.of(new ProductRow()));
		when(productsMock.get(2)).thenReturn(Optional.empty());
		when(historyMock.getFullHistory(1)).thenReturn(list);
		
		injectMock("history", historyMock);
		injectMock("products", productsMock);
	}
	
	private void injectMock(String fieldName, Object mockedObject) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = ProductResource.class.getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(resourceUnderTest, mockedObject);
	}
	
	@Test(expected=NotFoundException.class)
	public void testNonexistantProduct() {
		resourceUnderTest.getBoxDetails(2);
	}
	
	@Test
	public void testExistingHistory() {
		List<StockDataPoint> dataPoints = resourceUnderTest.getBoxDetails(1);
		assertEquals(3, dataPoints.size());
		
		assertEquals(0, dataPoints.get(0).getQuantity());
		assertEquals(5, dataPoints.get(1).getQuantity());
		assertEquals(3, dataPoints.get(2).getQuantity());
	}
	
}
