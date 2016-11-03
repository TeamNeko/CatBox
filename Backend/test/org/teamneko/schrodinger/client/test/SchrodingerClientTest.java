package org.teamneko.schrodinger.client.test;

import org.junit.Test;
import org.teamneko.meowlib.dto.ProductSearchResult;
import org.teamneko.schrodinger.client.SchrodingerClient;

import com.sun.jersey.api.client.UniformInterfaceException;

public class SchrodingerClientTest {
	private SchrodingerClient c;
	
	public SchrodingerClientTest() {
		c = new SchrodingerClient("http://localhost:8080/Frontend/rest");
	}
	
	@Test
	public void testGetValidUser() {
		c.requestUser("626262");
	}
	
	@Test(expected=UniformInterfaceException.class)
	public void testGetInvalidUser() {
		c.requestUser("1234");
	}
	
	@Test
	public void testSearchProduct() { 
		System.out.println(c.search("212345678902"));
		System.out.println(c.search("626262"));
		System.out.println(c.search("123456789152"));
	}
	
}
