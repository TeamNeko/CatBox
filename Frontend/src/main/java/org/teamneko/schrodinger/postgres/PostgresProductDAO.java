package org.teamneko.schrodinger.postgres;

import java.util.List;
import java.util.Optional;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.teamneko.meowlib.sql.ProductRow;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.sql.FilteredQueryRunner;

public class PostgresProductDAO implements ProductsDAO {
	private FilteredQueryRunner runner;
	private ResultSetHandler<ProductRow> productHandler = new BeanHandler<ProductRow>(ProductRow.class);
	private ResultSetHandler<List<ProductRow>> productListHandler = new BeanListHandler<ProductRow>(ProductRow.class);

	public PostgresProductDAO(PostgresDatabase database) {
		runner = new FilteredQueryRunner(database.getDataSource());
	}

	@Override
	public List<ProductRow> getProducts() {
		return runner.queryFiltered("SELECT * FROM products", productListHandler);
	}

	@Override
	public Optional<ProductRow> search(String barcode) {
		return Optional.ofNullable(runner.queryFiltered("SELECT * FROM products WHERE barcode=?", productHandler, barcode));
	}

	@Override
	public Optional<ProductRow> get(int id) {
		return Optional.ofNullable(runner.queryFiltered("SELECT * FROM products WHERE id=? LIMIT 1", productHandler, id));
	}

}
