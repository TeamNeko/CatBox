package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.json.TransactionRequest;
import org.teamneko.meowlib.json.TransactionRequest.Product;

public class TransactionRequestTest {
	
	private TransactionRequest testTransactionRequest;
	private TransactionRequest.Product testTransactProduct;
	private List<Product> productsModified;
	
	@Before 
	public void initiateTestTransactionRequest() {
		testTransactionRequest = new TransactionRequest();
		testTransactProduct = new TransactionRequest.Product();
		productsModified = new ArrayList();
	}
	
	@Test
	public void getSetTransactionRequestTest() 
	{
		assertEquals("",testTransactionRequest.getBox());
		assertEquals(-1,testTransactionRequest.getUser());
		assertTrue(testTransactionRequest.getProductsModified().isEmpty());
		
		testTransactProduct = new TransactionRequest.Product(1, 2);
		assertEquals(1,testTransactProduct.getId()); 
		assertEquals(2,testTransactProduct.getQuantity());
		
		testTransactProduct.setId(30);
		testTransactProduct.setQuantity(50);
		productsModified.add(testTransactProduct);
		testTransactionRequest.setBox("123BE");
		testTransactionRequest.setUser(1);
		testTransactionRequest.setProductsModified(productsModified);
		assertEquals("123BE",testTransactionRequest.getBox());
		assertEquals(1,testTransactionRequest.getUser());
		assertEquals(30,testTransactionRequest.getProductsModified().get(0).getId());
		assertEquals(50,testTransactionRequest.getProductsModified().get(0).getQuantity());
		
		testTransactionRequest = new TransactionRequest(100,"TESTINIT",new ArrayList());
		assertEquals("TESTINIT",testTransactionRequest.getBox());
		assertEquals(100,testTransactionRequest.getUser());
		assertTrue(testTransactionRequest.getProductsModified().isEmpty());
	}

}
