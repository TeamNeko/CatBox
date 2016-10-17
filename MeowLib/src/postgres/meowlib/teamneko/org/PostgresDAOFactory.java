package postgres.meowlib.teamneko.org;

import dao.meowlib.teamneko.org.AbstractDAOFactory;
import dao.meowlib.teamneko.org.ProductsDAO;

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
