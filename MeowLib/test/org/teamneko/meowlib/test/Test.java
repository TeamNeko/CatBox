package org.teamneko.meowlib.test;

import static org.junit.Assert.*;
import java.sql.SQLException;

import org.teamneko.meowlib.dao.ProductsDAO;
import org.teamneko.meowlib.obj.Product;
import org.teamneko.meowlib.postgres.PostgresDAOFactory;
import org.teamneko.meowlib.postgres.PostgresDatabase;

public class Test {

	@org.junit.Test
	public void test() {
		PostgresDatabase database = null;
		try {
			database = new PostgresDatabase("jdbc:postgresql://localhost/catbox?user=jaune&password=yolo");
		} catch (ClassNotFoundException | SQLException e) {
			fail(e.toString());
		}
		PostgresDAOFactory factory = new PostgresDAOFactory(database);
		ProductsDAO productDAO = factory.getProductsDAO();
		for(Product product: productDAO.getProducts()) {
			System.out.println(product);
		}
	}
}
