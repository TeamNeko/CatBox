package org.teamneko.meowlib.test;

import static org.junit.Assert.*;
import java.sql.SQLException;

import dao.meowlib.teamneko.org.ProductsDAO;
import obj.meowlib.teamneko.org.Product;
import postgres.meowlib.teamneko.org.PostgresDAOFactory;
import postgres.meowlib.teamneko.org.PostgresDatabase;

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
