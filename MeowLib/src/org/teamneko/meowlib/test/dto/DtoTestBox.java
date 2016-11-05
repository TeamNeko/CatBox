package org.teamneko.meowlib.test.dto;

import org.teamneko.meowlib.dto.Box;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DtoTestBox {
	private Box testBox;
	private Date created;
	private Date modified;
	
	@Before
	public void initiateTestBox()
	{
		created = new Date();
		modified = new Date();
		created.setTime(2000);
		modified.setTime(1999); 
		testBox = new Box("111",1,(float)1.0,created,modified,"1");
	}
	
	@Test
	public void initalisationBoxTest() 
	{
		assertEquals("111", testBox.getBarcode());
		assertEquals(1,testBox.getId());
		assertEquals(1.0,testBox.getWeight(),0.0f);
		assertEquals(created,testBox.getCreated());
		assertEquals(modified,testBox.getModified());
		assertEquals("1",testBox.getSize());
	} 
	
	
	@Test
	public void getsetBoxTest()
	{
		created.setTime(2550);
		modified.setTime(2500); 
		testBox.setBarcode("123");
		testBox.setId(2);
		testBox.setWeight((float)3.14);
		testBox.setCreated(created);
		testBox.setModified(modified);
		testBox.setSize("2");

		assertEquals("123", testBox.getBarcode());
		assertEquals(2,testBox.getId());
		assertEquals(3.14,testBox.getWeight(),0.001);
		assertEquals(created,testBox.getCreated());
		assertEquals(modified,testBox.getModified());
		assertEquals("2",testBox.getSize());
	}
	
	@Test
	public void toStringBoxTest()
	{
		created.setTime(2000);
		modified.setTime(1999); 
		String toString = "Box [barcode=111, id=1, weight=1.0, created=" + created + ", modified="
				+ modified + ", size=1]";
		assertEquals(testBox.toString(),toString);
	}

}
