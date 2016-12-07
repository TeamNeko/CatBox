package org.teamneko.schrodinger.postgres.test;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.dbunit.Assertion;
import org.dbunit.DataSourceBasedDBTestCase;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.teamneko.meowlib.sql.AlertRow;
import org.teamneko.schrodinger.dao.AlertsDAO;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDatabase;

public class PostgresAlertsDAOTest extends DataSourceBasedDBTestCase {	
	private PostgresDatabase postgres;
	private AlertsDAO daoUnderTest;
	
	public PostgresAlertsDAOTest() {
		postgres = TestPostgresDatabase.get();
		daoUnderTest = new PostgresDAOFactory(postgres).getAlertsDAO();
	}
	
	@Override
	protected DataSource getDataSource() {
		return postgres.getDataSource();
	}

	@Override
	protected IDataSet getDataSet() throws Exception {		
		return new XmlDataSet(new InputStreamReader(new FileInputStream("src/test/xml/alerts.xml"), StandardCharsets.UTF_8));
	}
	
	@Test
	public void testGetAlert() {
		Optional<AlertRow> optionalRow = daoUnderTest.getAlert(1);
		assertTrue(optionalRow.isPresent());
		AlertRow row = optionalRow.get();
		
		assertEquals(row.getId(), 1);
		assertEquals(row.getId_message(), 1);
		assertEquals(row.getTime().getTime(), 0);
	}
	
	@Test
	public void testGetNoAlert() {
		Optional<AlertRow> optionalRow = daoUnderTest.getAlert(2);
		assertFalse(optionalRow.isPresent());
	}
	
	@Test
	public void testUpdateAlert() throws Exception {
		daoUnderTest.changeLevel(1, 2);

		ITable expectedTable = new XmlDataSet(new InputStreamReader(new FileInputStream("src/test/xml/alertsUpdate.xml"), StandardCharsets.UTF_8)).getTable("alerts");
		Assertion.assertEquals(expectedTable, getActualTable());
	}
	
	@Test
	public void testRemoveAlert() throws Exception {
		daoUnderTest.removeAlert(1);
		
		ITable expectedTable = new XmlDataSet(new InputStreamReader(new FileInputStream("src/test/xml/alertsRemove.xml"), StandardCharsets.UTF_8)).getTable("alerts");
		Assertion.assertEquals(expectedTable, getActualTable());
	}
	
	@Test
	public void testAddAlert() throws Exception {
		daoUnderTest.addAlert(2, 1);
		
		assertEquals(getActualTable().getRowCount(), 2);
	}
	
	private ITable getActualTable() throws DataSetException, SQLException, Exception {
		return getConnection().createDataSet(new String[]{"alerts"}).getTable("alerts");
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.DELETE_ALL;
    }
}
