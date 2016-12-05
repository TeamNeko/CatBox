package org.teamneko.schrodinger.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.sql.InventoryRow;
import org.teamneko.schrodinger.dao.InventoryDAO;
import org.teamneko.schrodinger.sql.FilteredQueryRunner;

/**
 * The Class PostgresInventoryDAO.
 */
public class PostgresInventoryDAO implements InventoryDAO {
	
	/** The runner. */
	private FilteredQueryRunner runner;
	
	/** The item handler. */
	private ResultSetHandler<InventoryRow> itemHandler = new BeanHandler<InventoryRow>(InventoryRow.class);


	/**
	 * Instantiates a new postgres inventory DAO.
	 *
	 * @param database the database
	 */
	public PostgresInventoryDAO(PostgresDatabase database) {
		runner = new FilteredQueryRunner(database.getDataSource());
	}
	
	/* 
	 * @see org.teamneko.schrodinger.dao.InventoryDAO#get(int, int)
	 */
	public Optional<InventoryRow> get(int idBox, int idProduct) {
		return Optional.ofNullable(runner.queryFiltered("SELECT * FROM inventory WHERE id_product=? AND id_box = ? LIMIT 1", itemHandler, idProduct, idBox));
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.InventoryDAO#getBoxContents(int)
	 */
	@Override
	public List<NamedProduct> getBoxContents(int idBox) {
		return runner.queryFiltered(
			"SELECT quantity, id_product, products.name FROM inventory INNER JOIN products ON inventory.id_product = products.id WHERE inventory.id_box = ?",
			new ResultSetHandler<ArrayList<NamedProduct>>() {

				@Override
				public ArrayList<NamedProduct> handle(ResultSet rs) throws SQLException {
					ArrayList<NamedProduct> products = new ArrayList<NamedProduct>();

					while (rs.next()) {
						NamedProduct product = new NamedProduct();
						product.setId(rs.getInt("id_product"));
						product.setQuantity(rs.getInt("quantity"));
						product.setName(rs.getString("name"));
						products.add(product);
					}

					return products;
				}

			}, idBox);
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.InventoryDAO#getStock(int)
	 */
	@Override
	public int getStock(int idProduct) {
		return runner.queryFiltered("SELECT SUM(quantity) AS total FROM inventory WHERE id_product=?",
				new ResultSetHandler<Integer>() {
					@Override
					public Integer handle(ResultSet rs) throws SQLException {
						rs.next();
						return rs.getInt("total");
					}

				}, idProduct);
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.InventoryDAO#update(org.teamneko.meowlib.sql.InventoryRow)
	 */
	@Override
	public void update(InventoryRow item) {
		runner.updateFiltered("UPDATE inventory SET quantity = ? WHERE id = ?", item.getQuantity(), item.getId());
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.InventoryDAO#insert(org.teamneko.meowlib.sql.InventoryRow)
	 */
	@Override
	public void insert(InventoryRow item) {
		runner.updateFiltered("INSERT INTO inventory(id_product, id_box, quantity) VALUES (?, ?, ?)", item.getId_product(), item.getId_box(), item.getQuantity());
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.InventoryDAO#delete(org.teamneko.meowlib.sql.InventoryRow)
	 */
	@Override
	public void delete(InventoryRow item) {
		runner.updateFiltered("DELETE FROM inventory WHERE id=?", item.getId());
	}

}
