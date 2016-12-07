package org.teamneko.schrodinger.postgres;

import java.util.List;
import java.util.Optional;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.teamneko.meowlib.sql.ProductRow;
import org.teamneko.schrodinger.dao.ProductsDAO;
import org.teamneko.schrodinger.sql.FilteredQueryRunner;

/**
 * The Class PostgresProductDAO.
 */
public class PostgresProductDAO implements ProductsDAO {
	
	/** The runner. */
	private FilteredQueryRunner runner;
	
	/** The product handler. */
	private ResultSetHandler<ProductRow> productHandler = new BeanHandler<ProductRow>(ProductRow.class);
	
	/** The product list handler. */
	private ResultSetHandler<List<ProductRow>> productListHandler = new BeanListHandler<ProductRow>(ProductRow.class);

	/**
	 * Instantiates a new postgres product DAO.
	 *
	 * @param database the database
	 */
	public PostgresProductDAO(PostgresDatabase database) {
		runner = new FilteredQueryRunner(database.getDataSource());
	}

	/*
	 * @see org.teamneko.schrodinger.dao.ProductsDAO#getProducts()
	 */
	@Override
	public List<ProductRow> getProducts() {
		return runner.queryFiltered("SELECT * FROM products", productListHandler);
	}

	/*
	 * @see org.teamneko.schrodinger.dao.ProductsDAO#search(java.lang.String)
	 */
	@Override
	public Optional<ProductRow> search(String barcode) {
		return Optional.ofNullable(runner.queryFiltered("SELECT * FROM products WHERE barcode=?", productHandler, barcode));
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.ProductsDAO#get(int)
	 */
	@Override
	public Optional<ProductRow> get(int id) {
		return Optional.ofNullable(runner.queryFiltered("SELECT * FROM products WHERE id=? LIMIT 1", productHandler, id));
	}

}
