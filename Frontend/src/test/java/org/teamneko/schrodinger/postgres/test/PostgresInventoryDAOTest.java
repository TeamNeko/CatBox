package org.teamneko.schrodinger.postgres.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.sql.InventoryRow;
import org.teamneko.schrodinger.dao.InventoryDAO;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDatabase;

public class PostgresInventoryDAOTest extends DataSourceBasedDBTestCase {
	private PostgresDatabase postgres;
	private InventoryDAO daoUnderTest;
	
	public PostgresInventoryDAOTest() {
		postgres = TestPostgresDatabase.get();
		daoUnderTest = new PostgresDAOFactory(postgres).getInventoryDAO();
	}
	
	@Override
	protected DataSource getDataSource() {
		return postgres.getDataSource();
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {		
		return new XmlDataSet(new InputStreamReader(new FileInputStream("src/test/xml/inventory.xml"), StandardCharsets.UTF_8));
	}

	@Test
	public void testGetValid() {
		Optional<InventoryRow> opRow = daoUnderTest.get(1, 1);
		assertTrue(opRow.isPresent());
		InventoryRow row = opRow.get();
		assertEquals(1, row.getId());
		assertEquals(1, row.getId_box());
		assertEquals(1, row.getId_product());
		assertEquals(3, row.getQuantity());
	}
	
	@Test
	public void testGetInvalid() {
		Optional<InventoryRow> opRow = daoUnderTest.get(4, 1);
		assertFalse(opRow.isPresent());
	}
	
	@Test
	public void testGetStockedItemTotal() {
		assertEquals(10, daoUnderTest.getStock(1));
	}
	
	@Test
	public void testGetUnstockedItemTotal() {
		assertEquals(0, daoUnderTest.getStock(3));
	}
	
	@Test
	public void testGetFilledBoxContents() {
		List<NamedProduct> products = daoUnderTest.getBoxContents(1);
		assertEquals(2, products.size());
		assertEquals("Sample Product", products.get(0).getName());
		assertEquals(3, products.get(0).getQuantity());
		assertEquals("Dla Marde", products.get(1).getName());
		assertEquals(3, products.get(1).getQuantity());
	}
	
	@Test
	public void testGetEmptyBoxContents() {
		assertEquals(0, daoUnderTest.getBoxContents(4).size());
	}

	@Test
	public void testUpdate() throws DataSetException, SQLException, Exception {
		daoUnderTest.update(new InventoryRow(1, -1, -1, 10));
		assertEquals(10, getActualTable().getValue(0, "quantity"));
	}
	
	@Test
	public void testInsert() throws DataSetException, SQLException, Exception {
		daoUnderTest.insert(new InventoryRow(1, 4, 1, 5));
		assertEquals(4, getActualTable().getRowCount());
	}
	
	@Test
	public void testDelete() throws DataSetException, SQLException, Exception {
		daoUnderTest.delete(new InventoryRow(1, -1, -1, -1));
		assertEquals(2, getActualTable().getRowCount());
	}
	
	private ITable getActualTable() throws DataSetException, SQLException, Exception {
		return getConnection().createDataSet(new String[]{"inventory"}).getTable("inventory");
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
}
