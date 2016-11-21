package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.sql.InventoryRow;

public class SqlTestInventoryRow {

	InventoryRow testInventoryRow;
	
	@Before
	public void initiateTestInventoryRow() {
		testInventoryRow = new InventoryRow();
	}
	
	@Test
	public void getSetInventoryRowTest() {
		testInventoryRow.setId(1);
		testInventoryRow.setQuantity(2);
		testInventoryRow.setId_product(3);
		testInventoryRow.setId_box(4);
		assertEquals(1,testInventoryRow.getId());
		assertEquals(2,testInventoryRow.getQuantity());
		assertEquals(3,testInventoryRow.getId_product());
		assertEquals(4,testInventoryRow.getId_box());
	}
	
	@Test
	public void toStringInventoryRowTest() {
		testInventoryRow = new InventoryRow(10, 10, 10, 10);
		assertEquals("InventoryItem [id=10, id_box=10, id_product=10, quantity=10]", testInventoryRow.toString());
	}

}
