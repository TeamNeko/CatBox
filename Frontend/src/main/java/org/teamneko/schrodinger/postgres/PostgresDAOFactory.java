package org.teamneko.schrodinger.postgres;

import org.teamneko.schrodinger.dao.AbstractDAOFactory;
import org.teamneko.schrodinger.dao.AlertsDAO;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.dao.HistoryDAO;
import org.teamneko.schrodinger.dao.InventoryDAO;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.dao.TransactionsDAO;
import org.teamneko.schrodinger.dao.UsersDAO;

/**
 * A factory for creating PostgresDAO objects.
 */
public class PostgresDAOFactory extends AbstractDAOFactory {
	
	/** The database. */
	PostgresDatabase database;

	/**
	 * Instantiates a new postgres DAO factory.
	 *
	 * @param database the database
	 */
	public PostgresDAOFactory(PostgresDatabase database) {
		this.database = database;
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getAlertsDAO()
	 */
	@Override
	public AlertsDAO getAlertsDAO() {
		return new PostgresAlertsDAO(database);
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getBoxesDAO()
	 */
	@Override
	public BoxesDAO getBoxesDAO() {
		return new PostgresBoxesDAO(database);
	}

	/*
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getHistoryDAO()
	 */
	@Override
	public HistoryDAO getHistoryDAO() {
		return new PostgresHistoryDAO(database);
	}
	
	/*
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getInventoryDAO()
	 */
	@Override
	public InventoryDAO getInventoryDAO() {
		return new PostgresInventoryDAO(database);
	}

	/*
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getProductsDAO()
	 */
	@Override
	public ProductsDAO getProductsDAO() {
		return new PostgresProductDAO(database);
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getTransactionsDAO()
	 */
	@Override
	public TransactionsDAO getTransactionsDAO() {
		return new PostgresTransactionsDAO(database);
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getUsersDAO()
	 */
	@Override
	public UsersDAO getUsersDAO() {
		return new PostgresUsersDAO(database);
	}

}
