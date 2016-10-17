package postgres.meowlib.teamneko.org;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.meowlib.teamneko.org.ProductsDAO;
import obj.meowlib.teamneko.org.Product;
import sql.meowlib.teamneko.org.Transmuter;

public class PostgresProductDAO implements ProductsDAO {
	private PostgresDatabase database;
	
	public PostgresProductDAO(PostgresDatabase database) {
		this.database = database;
	}
	
	@Override
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		
		try {
			ResultSet rs = database.executeQuery("SELECT * FROM \"Products\"");
			while(rs.next())
				products.add(Transmuter.transmute(rs, Product.class));
			
		} catch(SQLException e) {
			
		}
		
		return products;
	}

}
