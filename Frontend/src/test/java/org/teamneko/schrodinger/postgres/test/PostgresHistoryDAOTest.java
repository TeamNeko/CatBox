package org.teamneko.schrodinger.postgres.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.teamneko.meowlib.sql.HistoryRow;
import org.teamneko.schrodinger.dao.HistoryDAO;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDatabase;

public class PostgresHistoryDAOTest extends DataSourceBasedDBTestCase {
	private PostgresDatabase postgres;
	private HistoryDAO daoUnderTest;
	
	public PostgresHistoryDAOTest() {
		postgres = TestPostgresDatabase.get();
		daoUnderTest = new PostgresDAOFactory(postgres).getHistoryDAO();
	}
	
	@Override
	protected DataSource getDataSource() {
		return postgres.getDataSource();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {		
		return new XmlDataSet(new InputStreamReader(new FileInputStream("src/test/xml/history.xml"), StandardCharsets.UTF_8));
	}
	
	@Test
	public void testAdd() throws Exception {
		HistoryRow row = new HistoryRow();
		row.setId(-1);
		row.setId_product(1);
		row.setQuantity(5);
		daoUnderTest.add(row);
		
		assertEquals(4, getActualTable().getRowCount());
		assertEquals(1, getActualTable().getValue(3, "id_product"));
		assertEquals(5, getActualTable().getValue(3, "quantity"));
	}
	
	@Test
	public void testGetFullHistory() {
		List<HistoryRow> fullHistory = daoUnderTest.getFullHistory(1);
		assertEquals(3, fullHistory.size());
	}

	private ITable getActualTable() throws Exception {
		return getConnection().createDataSet(new String[]{"history"}).getTable("history");
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
}
