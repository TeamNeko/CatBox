package org.teamneko.schrodinger.fx.test;
 
import static org.junit.Assert.*;

import org.junit.Test;
import org.teamneko.schrodinger.backend.fx.ModifiedProduct;

public class testModifiedProduct {
	ModifiedProduct testModifiedProduct;
	@Test 
	public void getSetTestModifiedProduct() {
	 
	    testModifiedProduct = new ModifiedProduct(100, 200, "TEST");
	 
	    assertEquals(100,testModifiedProduct.getId());
	    assertEquals(200,testModifiedProduct.getQuantity());
	    assertEquals("TEST",testModifiedProduct.getName());
	    assertEquals(0,testModifiedProduct.getModifiedqty());
	
	    testModifiedProduct = new ModifiedProduct(1000, 2000, "TEST2", 5000);
	 
	    assertEquals(1000,testModifiedProduct.getId());
	    assertEquals(2000,testModifiedProduct.getQuantity());
	    assertEquals("TEST2",testModifiedProduct.getName());
	    assertEquals(5000,testModifiedProduct.getModifiedqty());
	 
	    testModifiedProduct.setId(50);
	    testModifiedProduct.setQuantity(60);
	    testModifiedProduct.setName("TEST3");
	    testModifiedProduct.setModifiedqty(70);
	} 
}
 