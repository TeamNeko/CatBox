package org.teamneko.schrodinger.api.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.teamneko.meowlib.json.Box;
import org.teamneko.meowlib.json.BoxSearchResult;
import org.teamneko.meowlib.json.Product;
import org.teamneko.meowlib.json.ProductSearchResult;
import org.teamneko.meowlib.json.SearchResult;
import org.teamneko.meowlib.json.User;
import org.teamneko.meowlib.json.UserSearchResult;
import org.teamneko.meowlib.sql.ProductRow;
import org.teamneko.schrodinger.api.SearchResource;
import org.teamneko.schrodinger.api.UserResource;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.dao.UsersDAO;

public class SearchResourceTest {
	private static String boxNumber = "1000";
	private static String productNumber = "1001";
	private static String userNumber = "1002";

	private SearchResource resource = new SearchResource();

	public SearchResourceTest() throws Exception {
		BoxesDAO boxesDaoMock = mock(BoxesDAO.class);
		ProductsDAO productsDaoMock = mock(ProductsDAO.class);
		UsersDAO usersDaoMock = mock(UsersDAO.class);

		Optional<Box> mockBox = Optional.of(new Box());
		Optional<ProductRow> mockProduct = Optional.of(new ProductRow());
		Optional<User> mockUser = Optional.of(new User());

		when(boxesDaoMock.search(boxNumber)).thenReturn(mockBox);
		when(boxesDaoMock.search(productNumber)).thenReturn(Optional.empty());
		when(boxesDaoMock.search(userNumber)).thenReturn(Optional.empty());

		when(productsDaoMock.search(boxNumber)).thenReturn(Optional.empty());
		when(productsDaoMock.search(productNumber)).thenReturn(mockProduct);
		when(productsDaoMock.search(userNumber)).thenReturn(Optional.empty());

		when(usersDaoMock.search(boxNumber)).thenReturn(Optional.empty());
		when(usersDaoMock.search(productNumber)).thenReturn(Optional.empty());
		when(usersDaoMock.search(userNumber)).thenReturn(mockUser);

		Field boxDaoField = SearchResource.class.getDeclaredField("boxes");
		boxDaoField.setAccessible(true);
		boxDaoField.set(resource, boxesDaoMock);

		Field procuctDaoField = SearchResource.class.getDeclaredField("products");
		procuctDaoField.setAccessible(true);
		procuctDaoField.set(resource, productsDaoMock);

		Field userDaoField = SearchResource.class.getDeclaredField("users");
		userDaoField.setAccessible(true);
		userDaoField.set(resource, usersDaoMock);
	}

	@Test
	public void testBox() {
		SearchResult result = resource.search(boxNumber);
		assertEquals(result.getType(), "box");
		assertEquals(result.getClass(), BoxSearchResult.class);
	}

	@Test
	public void testProduct() {
		SearchResult result = resource.search(productNumber);
		assertEquals(result.getType(), "product");
		assertEquals(result.getClass(), ProductSearchResult.class);
	}

	@Test
	public void testUser() {
		SearchResult result = resource.search(userNumber);
		assertEquals(result.getType(), "user");
		assertEquals(result.getClass(), UserSearchResult.class);
	}

}
