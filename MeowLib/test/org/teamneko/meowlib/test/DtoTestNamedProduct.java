package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.NamedProduct;

public class DtoTestNamedProduct {
	
	NamedProduct testNamedProduct;
	@Before
	public void initiateTestNamedProduct() {
		testNamedProduct = new NamedProduct();
	}
	
	@Test
	public void getSetNamedProductTest() {
		testNamedProduct.setId(1);
		testNamedProduct.setName("NOM");
		testNamedProduct.setQuantity(12);
		assertEquals(1,testNamedProduct.getId());
		assertEquals("NOM",testNamedProduct.getName());
		assertEquals(12,testNamedProduct.getQuantity());
	}
	
	@Test
	public void toStringNamedProductTest() {
		testNamedProduct = new NamedProduct(15, 40, "TEST");
		assertEquals("NamedProduct [id=15, quantity=40, name=TEST]", testNamedProduct.toString());
	}
}
