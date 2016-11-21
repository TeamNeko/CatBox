package org.teamneko.meowlib.test;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.sql.LocationRow;

public class SqlTestLocationRow {
	private int id;
	private int floor;
	private String row;
	private String shelf;
	private String shelf_level;
	private String pallet;
	private String building;
	LocationRow testLocationRow;
	
	@Before
	public void initiateTestLocationRow() {
		testLocationRow = new LocationRow();
	}
	
	@Test
	public void getSetLocationRowTest() {
		testLocationRow.setId(1);
		testLocationRow.setFloor(2);
		testLocationRow.setRow("ROW");
		testLocationRow.setShelf("SHELF");
		testLocationRow.setShelf_level("SHELFLVL");
		testLocationRow.setPallet("PALLET");
		testLocationRow.setBuilding("BUILDING");
		assertEquals(1,testLocationRow.getId());
		assertEquals(2,testLocationRow.getFloor());
		assertEquals("ROW",testLocationRow.getRow());
		assertEquals("SHELF",testLocationRow.getShelf());
		assertEquals("SHELFLVL",testLocationRow.getShelf_level());
		assertEquals("PALLET",testLocationRow.getPallet());
		assertEquals("BUILDING",testLocationRow.getBuilding());
	}

}
