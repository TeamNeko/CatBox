package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.sql.HistoryRow;

public class SqlTestHistoryRow {

	HistoryRow testHistoryRow;
	Timestamp time; 
	
	@Before
	public void initiateTestHistoryRow() {
		time = new Timestamp(1);
		testHistoryRow = new HistoryRow();
	}
	
	@Test
	public void getSetHistoryTest() {
		testHistoryRow.setId(1);
		testHistoryRow.setQuantity(2);
		testHistoryRow.setId_product(3);
		testHistoryRow.setTime(time);
		assertEquals(1,testHistoryRow.getId());
		assertEquals(2,testHistoryRow.getQuantity());
		assertEquals(3,testHistoryRow.getId_product());
		assertEquals(time,testHistoryRow.getTime());
	}
	
	@Test
	public void toStringHistoryRowTest() {
		testHistoryRow = new HistoryRow(10, 10, 10, time);
		assertEquals("HistoryItem [id=10, id_product=10, quantity=10, time=" + time	+ "]", testHistoryRow.toString());
	}

}
