package org.teamneko.schrodinger.sql.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.sql.Database;

public class DatabaseTest {

	@Test
	public void test() {
		Database db = new Database();
		db.contextInitialized(null);
		assertEquals(Database.getDAOFactory().getClass(), PostgresDAOFactory.class);
		db.contextDestroyed(null);
	}
}
