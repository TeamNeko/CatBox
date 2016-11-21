package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.Box;
import org.teamneko.meowlib.BoxSearchResult;
import org.teamneko.meowlib.Product;
import org.teamneko.meowlib.User;

public class DtoTestBoxSearchResult {
	private BoxSearchResult testBoxSearchResult;
	private Box testBox;
	
	@Before
	public void initiateLinkage()
	{
		testBoxSearchResult = new BoxSearchResult();
		testBox = new Box();
		testBox.setId(1);
	}
	
	@Test
	public void getsetBoxSearchResultTest() 
	{
		testBoxSearchResult.setBox(testBox);
		testBoxSearchResult.setType("TEST");
		assertEquals(testBox,testBoxSearchResult.getBox());
		assertEquals("TEST",testBoxSearchResult.getType());
	} 
	
	@Test
	public void toStringBoxSearchResultTest()
	{
		testBoxSearchResult.setBox(testBox);
		assertEquals("BoxSearchResult [box=" + testBox + "]", testBoxSearchResult.toString());
		Date created = new Date();
		Date modified = new Date();
		created.setTime(2000);
		modified.setTime(1999); 
		Box testBox2 = new Box("111",4,(float)1.0,created,modified,"1");
		BoxSearchResult testBoxSearchResult2 = new BoxSearchResult(testBox2);
		assertEquals("BoxSearchResult [box=" + testBox2 + "]", testBoxSearchResult2.toString());
	} 


}
