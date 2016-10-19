package frontend.catbox.teamneko.org;

import java.sql.SQLException;
import java.util.List;

import dao.meowlib.teamneko.org.ProductsDAO;
import obj.meowlib.teamneko.org.Product;
import postgres.meowlib.teamneko.org.PostgresDAOFactory;
import postgres.meowlib.teamneko.org.PostgresDatabase;

public class Database {
	private static List<Product> productList = loadProductList();
	
	public static List<Product> loadProductList() {
		try {
			PostgresDatabase database = new PostgresDatabase("jdbc:postgresql://localhost/catbox?user=jaune&password=yolo");
			PostgresDAOFactory factory = new PostgresDAOFactory(database);
			ProductsDAO productDAO = factory.getProductsDAO();
			return productDAO.getProducts();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static List<Product> getProductList() {
		return productList;
	}
}
