package org.teamneko.schrodinger.postgres.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.teamneko.meowlib.json.Box;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDatabase;

public class PostgresBoxesDAOTest extends DataSourceBasedDBTestCase {
	private PostgresDatabase postgres;
	private BoxesDAO daoUnderTest;
	
	public PostgresBoxesDAOTest() {
		postgres = TestPostgresDatabase.get();
		daoUnderTest = new PostgresDAOFactory(postgres).getBoxesDAO();
	}
	
	@Override
	protected DataSource getDataSource() {
		return postgres.getDataSource();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {		
		return new XmlDataSet(new InputStreamReader(new FileInputStream("src/test/xml/boxes.xml"), StandardCharsets.UTF_8));
	}
	
	@Test
	public void testExistsTrue() {
		assertTrue(daoUnderTest.exists("1234506781"));
	}
	
	@Test
	public void testExistsFalse() {
		assertFalse(daoUnderTest.exists("1234506789"));
	}
	
	@Test
	public void testGetIdValid() {
		assertEquals(1, daoUnderTest.getId("1234506781"));
	}
	
	@Test
	public void testGetIdInvalid() {
		assertEquals(-1, daoUnderTest.getId("1234506789"));
	}
	
	@Test
	public void testSearchValid() {
		Optional<Box> opBox = daoUnderTest.search("1234506782");
		assertTrue(opBox.isPresent());
		Box box = opBox.get();
		assertEquals(2, box.getId());
		assertEquals("1234506782", box.getBarcode());
		assertEquals("10x10x10", box.getSize());
	}
	
	@Test
	public void testSearchInvalid() {
		Optional<Box> opBox = daoUnderTest.search("1234506789");
		assertFalse(opBox.isPresent());
	}
	
	@Test
	public void testBoxCreation() throws DataSetException, SQLException, Exception {
		daoUnderTest.create("1234506785");
		
		ITable actualTable = getActualTable();
		assertEquals(5, actualTable.getRowCount());
		assertEquals("1234506785", actualTable.getValue(4, "barcode"));
	}
	
	private ITable getActualTable() throws DataSetException, SQLException, Exception {
		return getConnection().createDataSet(new String[]{"boxes"}).getTable("boxes");
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
}
