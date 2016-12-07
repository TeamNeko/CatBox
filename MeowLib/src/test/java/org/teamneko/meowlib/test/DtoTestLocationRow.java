package org.teamneko.meowlib.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.sql.LocationRow;

public class DtoTestLocationRow {
	
	LocationRow testLocationRow;
	@Before
	public void initiateLocationRowTest() {
		testLocationRow = new LocationRow();
	}
	
	@Test
	public void getsetLocationRowTest()
	{
		testLocationRow.setId(1);
		testLocationRow.setFloor(2);
		testLocationRow.setRow("TEST");
		testLocationRow.setShelf("TESTSHELF");
		testLocationRow.setShelf_level("TESTSHELFLVL");
		testLocationRow.setPallet("123");
		testLocationRow.setBuilding("TESTBLD");
		
		assertEquals(1,testLocationRow.getId());
		assertEquals(2,testLocationRow.getFloor());
		assertEquals("TEST",testLocationRow.getRow());
		assertEquals("TESTSHELF",testLocationRow.getShelf());
		assertEquals("TESTSHELFLVL",testLocationRow.getShelf_level());
		assertEquals("123",testLocationRow.getPallet());
		assertEquals("TESTBLD",testLocationRow.getBuilding());
	}
}
