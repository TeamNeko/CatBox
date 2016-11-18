package org.teamneko.meowlib.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.json.User;

public class DtoTestUser {
	
	User testUser;
	
	@Before
	public void initiateTestUser() {
		testUser = new User();
	}
	
	@Test
	public void getSetUserTest()
	{
		testUser.setId(123);
		testUser.setFirstName("FIRST");
		testUser.setLastName("LAST");
		testUser.setNumber("123");
		testUser.setType("USER");
		
		assertEquals(123,testUser.getId());
		assertEquals("FIRST",testUser.getFirstName());
		assertEquals("LAST",testUser.getLastName());
		assertEquals("123",testUser.getNumber());
		assertEquals("USER",testUser.getType());			
	}
    
	@Test
	public void toStringUserTest()
	{
		testUser = new User(123,"FIRST","LAST","123","USER");
		assertEquals("User [id=123, firstName=FIRST, lastName=LAST, number=123, type=USER]",testUser.toString());
	}
}
