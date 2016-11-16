package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.Location;

public class DtoTestLocation {
	
	Location testLocation;
	@Before
	public void initiateLocationTest() {
		testLocation = new Location();
	}
	
	@Test
	public void getsetLocationTest()
	{
		testLocation.setId(1);
		testLocation.setFloor(2);
		testLocation.setRow("TEST");
		testLocation.setShelf("TESTSHELF");
		testLocation.setShelf_level("TESTSHELFLVL");
		testLocation.setPallet("123");
		testLocation.setBuilding("TESTBLD");
		
		assertEquals(1,testLocation.getId());
		assertEquals(2,testLocation.getFloor());
		assertEquals("TEST",testLocation.getRow());
		assertEquals("TESTSHELF",testLocation.getShelf());
		assertEquals("TESTSHELFLVL",testLocation.getShelf_level());
		assertEquals("123",testLocation.getPallet());
		assertEquals("TESTBLD",testLocation.getBuilding());
	}
}
