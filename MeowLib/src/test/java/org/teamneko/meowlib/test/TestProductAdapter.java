package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.adapters.ProductAdapter;
import org.teamneko.meowlib.json.Product;
import org.teamneko.meowlib.sql.ProductRow;

public class TestProductAdapter {
	ProductRow testProductRow;
	Product testProduct;
	private Timestamp date_added;
	private Timestamp date_retired;
	
	@Before
	public void initiateAbstractProductAdapter() {
		date_added = new Timestamp(1);
		date_retired = new Timestamp(2);
		testProductRow = new ProductRow("", date_added, date_retired, "TESTDESC", 10, "TESTNAME", 10, 100.5);
		testProduct = new Product(10, "TESTNAME", "TESTDESC", (Date)date_added, (Date)date_retired, 100.5, 10);
	}
	  
	@Test  
	public void testToJson() {
		assertEquals(testProduct.toString(),ProductAdapter.toJSONProduct(testProductRow).toString());
	}
	
	@Test 
	public void testToSql() {
		assertEquals(testProductRow.toString(),ProductAdapter.toSQLProduct(testProduct).toString());
	}
}
