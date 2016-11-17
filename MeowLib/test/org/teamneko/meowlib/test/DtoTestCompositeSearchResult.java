package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.Box;
import org.teamneko.meowlib.BoxSearchResult;
import org.teamneko.meowlib.CompositeSearchResult;
import org.teamneko.meowlib.Product;
import org.teamneko.meowlib.User;


public class DtoTestCompositeSearchResult {
	private Box testBox;
	private Product testProduct;
	private User testUser;
	private Date created;
	private Date modified;
	
	@Before
	public void initiateLinkage()
	{
		Date created = new Date();
		Date modified = new Date();
		created.setTime(2000);
		modified.setTime(1999); 
		testBox = new Box("111",4,(float)1.0,created,modified,"1");
		testProduct = new Product();
		testProduct.setId(1);
		testProduct.setName("TEST");
		testProduct.setAdded(created);
		testProduct.setRemoved(modified);
		testProduct.setWeight(5);
		testUser = new User(100, "First Name", "Last Name", "123456", "Type");
		
	}
	
	@Test
	public void getsetCompositeSearchResultTest()
	{
		CompositeSearchResult testCompositeSearchResult = new CompositeSearchResult();
		testCompositeSearchResult.setBox(testBox);
		testCompositeSearchResult.setProduct(testProduct);
		testCompositeSearchResult.setUser(testUser);
		
		assertEquals(testBox,testCompositeSearchResult.getBox());
		assertEquals(testProduct,testCompositeSearchResult.getProduct());
		assertEquals(testUser,testCompositeSearchResult.getUser());
	}
	
	@Test
	public void initiateCompositeSearchResultTest()
	{
		CompositeSearchResult testCompositeSearchResult = new CompositeSearchResult("Type",testBox,testProduct,testUser);
		assertEquals(testBox,testCompositeSearchResult.getBox());
		assertEquals(testProduct,testCompositeSearchResult.getProduct());
		assertEquals(testUser,testCompositeSearchResult.getUser());
	}

}
