package org.teamneko.schrodinger.postgres;

import org.teamneko.schrodinger.dao.AbstractDAOFactory;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.dao.UsersDAO;

public class PostgresDAOFactory extends AbstractDAOFactory {
	PostgresDatabase database;
	
	public PostgresDAOFactory(PostgresDatabase database) {
		this.database = database;
	}

	@Override
	public ProductsDAO getProductsDAO() {
		return new PostgresProductDAO(database);
	}

	@Override
	public UsersDAO getUsersDAO() {
		return new PostgresUsersDAO(database);
	}

	@Override
	public BoxesDAO getBoxesDAO() {
		return new PostgresBoxesDAO(database);
	}
	
}
