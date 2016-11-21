package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.sql.AlertRow;

public class SqlTestAlertRow { 

	Timestamp time; 
	AlertRow testAlertRow;
	
	@Before
	public void initiateTestAlertRow() {
		time = new Timestamp(1);
		testAlertRow = new AlertRow();
	}
	
	@Test
	public void getSetAlertRowTest() {
		testAlertRow.setId(1);
		testAlertRow.setId_message(2);
		testAlertRow.setId_product(3);
		testAlertRow.setTime(time);
		assertEquals(1,testAlertRow.getId());
		assertEquals(2,testAlertRow.getId_message());
		assertEquals(3,testAlertRow.getId_product());
		assertEquals(time,testAlertRow.getTime());
	}
	
	@Test
	public void toStringAlertRowTest() {
		testAlertRow = new AlertRow(10, 10, 10, time);
		assertEquals("Alert [id=10, id_product=10, id_message=10, time=" + time + "]", testAlertRow.toString());
	}

}
