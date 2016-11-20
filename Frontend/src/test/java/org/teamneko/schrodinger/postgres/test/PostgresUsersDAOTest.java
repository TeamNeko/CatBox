package org.teamneko.schrodinger.postgres.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.teamneko.meowlib.json.User;
import org.teamneko.schrodinger.dao.UsersDAO;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDatabase;

public class PostgresUsersDAOTest extends DataSourceBasedDBTestCase {
	private PostgresDatabase postgres;
	private UsersDAO daoUnderTest;
	
	public PostgresUsersDAOTest() {
		postgres = TestPostgresDatabase.get();
		daoUnderTest = new PostgresDAOFactory(postgres).getUsersDAO();
	}
	
	@Override
	protected DataSource getDataSource() {
		return postgres.getDataSource();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {		
		return new XmlDataSet(new InputStreamReader(new FileInputStream("src/test/xml/users.xml"), StandardCharsets.UTF_8));
	}
	
	@Test
	public void testSearchValid() {
		Optional<User> opUser = daoUnderTest.search("5757575757");
		assertTrue(opUser.isPresent());
		User user = opUser.get();
		assertEquals(1, user.getId());
		assertEquals("Tommy", user.getFirstName());
		assertEquals("Savaria", user.getLastName());
		assertEquals("Tabby Cat", user.getType());
	}
	
	@Test
	public void testSearchInvalid() {
		Optional<User> opUser = daoUnderTest.search("1111111");
		assertFalse(opUser.isPresent());
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
}
