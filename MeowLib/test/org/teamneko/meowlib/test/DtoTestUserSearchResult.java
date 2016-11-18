package org.teamneko.meowlib.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.teamneko.meowlib.json.User;
import org.teamneko.meowlib.json.UserSearchResult;

public class DtoTestUserSearchResult {

	UserSearchResult testUserSearchResult;
	User testUser;
	
	@Before
	public void intiateProductSearchResult() 
	{
		testUser = new User(123,"FIRST","LAST","123","USER");
	}
	
	@Test
	public void getSetUserSearchResult()
	{
		testUserSearchResult = new UserSearchResult();
		testUserSearchResult.setUser(testUser);
		testUserSearchResult.setType("TEST");
		assertEquals(testUser,testUserSearchResult.getUser());
		assertEquals("TEST",testUserSearchResult.getType());
	}
	
	@Test
	public void toStringProductSearchResult()
	{
		testUserSearchResult = new UserSearchResult(testUser);
		assertEquals("UserSearchResult [user=" + testUser.toString() + "]",testUserSearchResult.toString());
	}

}
