package org.teamneko.meowlib.postgres;

import org.teamneko.meowlib.dao.AbstractDAOFactory;
import org.teamneko.meowlib.dao.ProductsDAO;

public class PostgresDAOFactory extends AbstractDAOFactory {
	PostgresDatabase database;
	
	public PostgresDAOFactory(PostgresDatabase database) {
		this.database = database;
	}

	@Override
	public ProductsDAO getProductsDAO() {
		return new PostgresProductDAO(database);
	}
	
}
