package org.teamneko.schrodinger.postgres.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.teamneko.meowlib.sql.ProductRow;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDatabase;

public class PostgresProductDAOTest extends DataSourceBasedDBTestCase {
	private PostgresDatabase postgres;
	private ProductsDAO daoUnderTest;
	
	public PostgresProductDAOTest() {
		postgres = TestPostgresDatabase.get();
		daoUnderTest = new PostgresDAOFactory(postgres).getProductsDAO();
	}
	
	@Override
	protected DataSource getDataSource() {
		return postgres.getDataSource();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {		
		return new XmlDataSet(new InputStreamReader(new FileInputStream("src/test/xml/products.xml"), StandardCharsets.UTF_8));
	}
	
	@Test
	public void testGetProducts() {
		List<ProductRow> products = daoUnderTest.getProducts();
		assertEquals(3, products.size());
	}
	
	@Test
	public void testSearch() {
		Optional<ProductRow> opProduct = daoUnderTest.search("1234567891");
		assertTrue(opProduct.isPresent());
		ProductRow product = opProduct.get();
		assertEquals(2, product.getId());
		assertEquals("Dla Marde", product.getName());
		assertEquals("Maudit que les tests unitaire ca fait chier", product.getDescription());
	}
	
	@Test
	public void searchInvalid() {
		Optional<ProductRow> opProduct = daoUnderTest.search("1234567895");
		assertFalse(opProduct.isPresent());
	}
	
	@Test
	public void testGet() {
		Optional<ProductRow> opProduct = daoUnderTest.get(3);
		assertTrue(opProduct.isPresent());
		ProductRow product = opProduct.get();
		assertEquals(3, product.getId());
		assertEquals("Des Unit Tests", product.getName());
		assertEquals("Fuck DBUnit pis JUnit", product.getDescription());
	}
	
	@Test
	public void testGetInvalid() {
		Optional<ProductRow> opProduct = daoUnderTest.get(5);
		assertFalse(opProduct.isPresent());
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
}
