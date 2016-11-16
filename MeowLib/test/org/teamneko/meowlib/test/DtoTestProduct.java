package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.Product;

public class DtoTestProduct {

	Product testProduct;
	private Date created;
	private Date removed;
	
	@Before
	public void initiateProductTest() {
		created = new Date();
		removed = new Date();
		created.setTime(2000);
		removed.setTime(1999); 
		testProduct = new Product();
	}
	
	@Test
	public void getsetProductTest()
	{
		testProduct.setId(1);
		testProduct.setName("TEST");
		testProduct.setDescription("TESTDESC");
		testProduct.setAdded(created);
		testProduct.setRemoved(removed);
		testProduct.setWeight(1.1);
		
		assertEquals(1,testProduct.getId());
		assertEquals("TEST",testProduct.getName());
		assertEquals("TESTDESC",testProduct.getDescription());
		assertEquals(created,testProduct.getAdded());
		assertEquals(removed,testProduct.getRemoved());
		assertEquals(1.1,testProduct.getWeight(),0.01);
	} 
	
	@Test
	public void toStringProductTest()
	{
		testProduct.setId(1);
		testProduct.setName("TEST");
		testProduct.setDescription("TESTDESC");
		testProduct.setAdded(created);
		testProduct.setRemoved(removed);
		testProduct.setWeight(1.1);
		assertEquals("Product [id=1, name=TEST, description=TESTDESC, added=" + created
				+ ", removed=" + removed + ", weight=1.1]", testProduct.toString());
	}
}
