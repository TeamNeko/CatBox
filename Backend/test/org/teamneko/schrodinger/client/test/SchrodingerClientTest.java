package org.teamneko.schrodinger.client.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.teamneko.meowlib.BoxSearchResult;
import org.teamneko.meowlib.NamedProduct;
import org.teamneko.meowlib.ProductSearchResult;
import org.teamneko.meowlib.TransactionRequest;
import org.teamneko.meowlib.User;
import org.teamneko.meowlib.UserSearchResult;
import org.teamneko.schrodinger.client.SchrodingerClient;

import com.sun.jersey.api.client.UniformInterfaceException;

public class SchrodingerClientTest {
	private SchrodingerClient c;
	
	public SchrodingerClientTest() {
		c = new SchrodingerClient("http://localhost:8080/Frontend/rest");
	}
	
	@Test
	public void testGetValidUser() {
		User result = c.requestUser("626262");
		assertEquals(1, result.getId());
		assertEquals("Bob", result.getFirstName());
		assertEquals("Fan", result.getLastName());
		assertEquals("626262", result.getNumber());
		assertEquals("Slave", result.getType());
	}
	
	@Test(expected=UniformInterfaceException.class)
	public void testGetInvalidUser() {		c.requestUser("1234");
	}
	
	@Test
	public void testSearchProduct() {
		assertEquals(ProductSearchResult.class, c.search("212345678902").getClass());
		assertEquals(UserSearchResult.class, c.search("626262").getClass());
		assertEquals(BoxSearchResult.class, c.search("123456789152").getClass());
	}
	
	
	public void testPostTransaction() {
		TransactionRequest transaction = new TransactionRequest();
		List<TransactionRequest.Product> modifiedProducts = transaction.getProductsModified();
		
		transaction.setUser(1);
		transaction.setBox("123456789152");
		
		modifiedProducts.add(new TransactionRequest.Product(1, 5));
		modifiedProducts.add(new TransactionRequest.Product(2, 5));
		
		c.postTransaction(transaction);
	}
	
	@Test
	public void testGetBox() {
		for(NamedProduct product : c.getBoxDetails(3))
			System.out.println(product);
	}
	
}
