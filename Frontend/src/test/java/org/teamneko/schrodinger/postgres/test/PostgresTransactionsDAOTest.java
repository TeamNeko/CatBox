package org.teamneko.schrodinger.postgres.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.teamneko.meowlib.json.Transaction;
import org.teamneko.schrodinger.dao.TransactionsDAO;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDatabase;

public class PostgresTransactionsDAOTest extends DataSourceBasedDBTestCase {
	private PostgresDatabase postgres;
	private TransactionsDAO daoUnderTest;
	
	public PostgresTransactionsDAOTest() {
		postgres = TestPostgresDatabase.get();
		daoUnderTest = new PostgresDAOFactory(postgres).getTransactionsDAO();
	}

	@Override
	protected DataSource getDataSource() {
		return postgres.getDataSource();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new XmlDataSet(new InputStreamReader(new FileInputStream("src/test/xml/transaction.xml"), StandardCharsets.UTF_8));
	}
	
	@Test
	public void testAddTransaction() throws DataSetException, SQLException, Exception {
		Transaction t = new Transaction();
		t.setIdBox(1);
		t.setIdProduct(1);
		t.setIdUser(1);
		t.setType("add");
		t.setQuantity(5);
		daoUnderTest.addTransaction(t);
		
		ITable actualTable = getActualTable();
		assertEquals(1, actualTable.getRowCount());
		assertEquals(1, actualTable.getValue(0, "id_box"));
		assertEquals(1, actualTable.getValue(0, "id_product"));
		assertEquals(1, actualTable.getValue(0, "id_user"));
		assertEquals("add", actualTable.getValue(0, "type"));
		assertEquals(5, actualTable.getValue(0, "quantity"));	
	}
	
	private ITable getActualTable() throws DataSetException, SQLException, Exception {
		return getConnection().createDataSet(new String[]{"transactions"}).getTable("transactions");
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
}
