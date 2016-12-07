package org.teamneko.schrodinger.dao.test;

import static org.junit.Assert.*;

import org.teamneko.schrodinger.dao.NullDAOFactory;
import org.junit.Test;

public class NullDAOFactoryTest {
	private NullDAOFactory nullDaoFactory = new NullDAOFactory();
	
	@Test
	public void testAlertsDao() {
		assertNull(nullDaoFactory.getAlertsDAO());
	}
	
	@Test
	public void testBoxesDao() {
		assertNull(nullDaoFactory.getBoxesDAO());
	}
	
	@Test
	public void testHistoryDao() {
		assertNull(nullDaoFactory.getHistoryDAO());
	}
	
	@Test
	public void testInventoryDao() {
		assertNull(nullDaoFactory.getInventoryDAO());
	}

	@Test
	public void testProductsDao() {
		assertNull(nullDaoFactory.getProductsDAO());
	}
	
	@Test
	public void testTransactionsDao() {
		assertNull(nullDaoFactory.getTransactionsDAO());
	}
	
	@Test
	public void testUsersDao() {
		assertNull(nullDaoFactory.getUsersDAO());
	}
}