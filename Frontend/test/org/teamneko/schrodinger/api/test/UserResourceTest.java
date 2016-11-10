package org.teamneko.schrodinger.api.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Optional;

import org.junit.Test;
import org.teamneko.meowlib.dto.User;
import org.teamneko.schrodinger.api.UserResource;
import org.teamneko.schrodinger.dao.UsersDAO;

import com.sun.jersey.api.NotFoundException;

public class UserResourceTest {
	private static long mockId = 1;
	private static String mockFirstName = "Victor";
	private static String mockLastName = "Fan";
	private static String mockNumber = "6262";
	private static String mockType = "slave";
	private static String mockInvalidNumber = "404";

	private static UserResource resource = new UserResource();

	public UserResourceTest() throws Exception {
		UsersDAO usersDaoMock = mock(UsersDAO.class);

		Optional<User> slave = Optional.of(new User(mockId, mockFirstName, mockLastName, mockNumber, mockType));
		when(usersDaoMock.search(mockNumber)).thenReturn(slave);
		when(usersDaoMock.search(mockInvalidNumber)).thenReturn(Optional.empty());

		Field daoField = UserResource.class.getDeclaredField("dao");
		daoField.setAccessible(true);
		daoField.set(resource, usersDaoMock);
	}

	@Test
	public void test() {
		User user = resource.getUser(mockNumber);
		assertEquals(user.getId(), mockId);
		assertEquals(user.getFirstName(), mockFirstName);
		assertEquals(user.getLastName(), mockLastName);
		assertEquals(user.getNumber(), mockNumber);
		assertEquals(user.getType(), mockType);
	}

	@Test(expected=NotFoundException.class)
	public void testGetInvalidUser() {
		resource.getUser(mockInvalidNumber);
	}

}
