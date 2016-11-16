package org.teamneko.schrodinger.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.teamneko.meowlib.Product;
import org.teamneko.schrodinger.dao.ProductsDAO;

public class PostgresProductDAO implements ProductsDAO {
	private QueryRunner runner;
	private ResultSetHandler<Product> productHandler = new BeanHandler<Product>(Product.class);

	public PostgresProductDAO(PostgresDatabase database) {
		runner = new QueryRunner(database.getDataSource());
	}

	@Override
	public List<Product> getProducts() {
		try {
			return runner.query("SELECT * FROM products", new ResultSetHandler<List<Product>>() {

				@Override
				public List<Product> handle(ResultSet rs) throws SQLException {
					List<Product> products = new ArrayList<Product>();

					while (rs.next()) {
						Product product = new Product();
						product.setId(rs.getInt("id"));
						product.setName(rs.getString("name"));
						product.setDescription(rs.getString("description"));
						product.setAdded(rs.getDate("date_added"));
						product.setRemoved(rs.getDate("date_retired"));
						product.setWeight(rs.getDouble("weight"));
						products.add(product);
					}
					;

					return products;
				}

			});
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<Product>(0);
	}

	@Override
	public Optional<Product> search(String barcode) {
		try {
			return Optional.of(runner.query("SELECT * FROM products WHERE barcode=?", new ResultSetHandler<Product>() {

				@Override
				public Product handle(ResultSet rs) throws SQLException {
					rs.next();
					Product product = new Product();
					product.setId(rs.getInt("id"));
					product.setName(rs.getString("name"));
					product.setDescription(rs.getString("description"));
					product.setAdded(rs.getDate("date_added"));
					product.setRemoved(rs.getDate("date_retired"));
					product.setWeight(rs.getDouble("weight"));
					return product;
				}

			}, barcode));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public Optional<Product> get(int id) {
		try {
			return Optional.of(runner.query("SELECT * FROM products WHERE id=? LIMIT 1", productHandler, id));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

}
