package org.teamneko.schrodinger.postgres;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.teamneko.meowlib.sql.ProductRow;
import org.teamneko.schrodinger.dao.ProductsDAO;

public class PostgresProductDAO implements ProductsDAO {
	private QueryRunner runner;
	private ResultSetHandler<ProductRow> productHandler = new BeanHandler<ProductRow>(ProductRow.class);
	private ResultSetHandler<List<ProductRow>> productListHandler = new BeanListHandler<ProductRow>(ProductRow.class);

	public PostgresProductDAO(PostgresDatabase database) {
		runner = new QueryRunner(database.getDataSource());
	}

	@Override
	public List<ProductRow> getProducts() {
		try {
			return runner.query("SELECT * FROM products", productListHandler);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<ProductRow>(0);
	}

	@Override
	public Optional<ProductRow> search(String barcode) {
		try {
			return Optional.ofNullable(runner.query("SELECT * FROM products WHERE barcode=?", productHandler, barcode));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public Optional<ProductRow> get(int id) {
		try {
			return Optional.ofNullable(runner.query("SELECT * FROM products WHERE id=? LIMIT 1", productHandler, id));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

}
