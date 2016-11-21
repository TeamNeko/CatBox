package org.teamneko.meowlib.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.json.Transaction;

public class DtoTestTransaction {

	private Transaction testTransaction;
	
	@Before
	public void initiateTestTransaction() {
		testTransaction = new Transaction();
	}
	
	@Test
	public void getsetTransactionTest() {
		assertEquals(-1, testTransaction.getIdBox());
		assertEquals(-1, testTransaction.getIdProduct());
		assertEquals(-1, testTransaction.getIdUser());
		assertEquals(-1, testTransaction.getQuantity());
		assertEquals(-1, testTransaction.getTime());
		assertEquals("", testTransaction.getType());
		
		testTransaction.setIdBox(40);
		testTransaction.setIdProduct(50);
		testTransaction.setIdUser(60);
		testTransaction.setQuantity(70); 
		testTransaction.setTime((long)80);
		testTransaction.setType("TEST");
		assertEquals(40, testTransaction.getIdBox());
		assertEquals(50, testTransaction.getIdProduct());
		assertEquals(60, testTransaction.getIdUser());
		assertEquals(70, testTransaction.getQuantity());
		assertEquals(80, testTransaction.getTime());
		assertEquals("TEST", testTransaction.getType());
		
		testTransaction = new Transaction(10,10,10,10,(long)10,"TEST2");
		assertEquals(10, testTransaction.getIdBox());
		assertEquals(10, testTransaction.getIdProduct());
		assertEquals(10, testTransaction.getIdUser());
		assertEquals(10, testTransaction.getQuantity());
		assertEquals(10, testTransaction.getTime());
		assertEquals("TEST2", testTransaction.getType());
	}

}
