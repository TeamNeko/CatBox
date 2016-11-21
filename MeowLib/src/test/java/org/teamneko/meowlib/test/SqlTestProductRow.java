package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.sql.ProductRow;

public class SqlTestProductRow {
	
	ProductRow testProductRow;
	private Timestamp date_added;
	private Timestamp date_retired;
	@Before
	public void initiateTestHistoryRow() {
		date_added = new Timestamp(1);
		date_retired = new Timestamp(2);
		testProductRow = new ProductRow();
	}
	
	@Test
	public void getSetHistoryTest() {
		testProductRow.setBarcode("BARCODE");
		testProductRow.setDate_added(date_added);
		testProductRow.setDate_retired(date_retired);
		testProductRow.setDescription("DESCRIPTION");
		testProductRow.setId(1);
		testProductRow.setName("NAME");
		testProductRow.setThreshold(3);
		testProductRow.setWeight(10.1);
		assertEquals("BARCODE",testProductRow.getBarcode());
		assertEquals(date_added,testProductRow.getDate_added());
		assertEquals(date_retired,testProductRow.getDate_retired());
		assertEquals("DESCRIPTION",testProductRow.getDescription());
		assertEquals(1,testProductRow.getId());
		assertEquals("NAME",testProductRow.getName());
		assertEquals(3,testProductRow.getThreshold());
		assertEquals(10.1,testProductRow.getWeight(),0.01);
	}
	
	@Test
	public void toStringHistoryRowTest() {
		testProductRow = new ProductRow("TESTBAR", date_added, date_retired, "TESTDESC", 10, "TESTNAME", 10, 100.5);
		assertEquals("ProductRow [barcode=TESTBAR, date_added=" + date_added + ", date_retired=" + date_retired
				+ ", description=TESTDESC, id=10, name=TESTNAME, threshold=10, weight=100.5]", testProductRow.toString());
	}

}
