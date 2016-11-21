package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.Product;
import org.teamneko.meowlib.ProductSearchResult;

public class DtoTestProductSearchResult {

	ProductSearchResult testProductSearchResult;
	Product testProduct;
	Date created;
	Date removed;
	
	@Before
	public void intiateProductSearchResult() 
	{
		created = new Date();
		removed = new Date();
		created.setTime(2000);
		removed.setTime(1999); 
		testProduct = new Product();
		testProductSearchResult = new ProductSearchResult();
		testProduct.setId(1);
		testProduct.setName("TEST");
		testProduct.setDescription("TESTDESC");
		testProduct.setAdded(created);
		testProduct.setRemoved(removed);
		testProduct.setWeight(1.1);
	}
	
	@Test
	public void getSetProductSearchResultTest()
	{
		testProductSearchResult.setProduct(testProduct);
		assertEquals(testProduct,testProductSearchResult.getProduct());
	}
	
	@Test
	public void toStringProductSearchResult()
	{
		testProductSearchResult = new ProductSearchResult(testProduct);
		assertEquals("ProductSearchResult [product=" + testProduct.toString() + ", type=product]",testProductSearchResult.toString());
	}

}
