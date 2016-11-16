package org.teamneko.schrodinger.postgres;

import org.teamneko.schrodinger.dao.AbstractDAOFactory;
import org.teamneko.schrodinger.dao.AlertsDAO;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.dao.InventoryDAO;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.dao.TransactionsDAO;
import org.teamneko.schrodinger.dao.UsersDAO;

public class PostgresDAOFactory extends AbstractDAOFactory {
	PostgresDatabase database;

	public PostgresDAOFactory(PostgresDatabase database) {
		this.database = database;
	}

	@Override
	public AlertsDAO getAlertsDAO() {
		return new PostgresAlertsDAO(database);
	}

	@Override
	public BoxesDAO getBoxesDAO() {
		return new PostgresBoxesDAO(database);
	}

	@Override
	public InventoryDAO getInventoryDAO() {
		return new PostgresInventoryDAO(database);
	}

	@Override
	public ProductsDAO getProductsDAO() {
		return new PostgresProductDAO(database);
	}

	@Override
	public TransactionsDAO getTransactionsDAO() {
		return new PostgresTransactionsDAO(database);
	}

	@Override
	public UsersDAO getUsersDAO() {
		return new PostgresUsersDAO(database);
	}

}
