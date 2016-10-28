package org.teamneko.meowlib.postgres;

import org.teamneko.meowlib.dao.AbstractDAOFactory;
import org.teamneko.meowlib.dao.ProductsDAO;
import org.teamneko.meowlib.dao.UsersDAO;

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
	
}
