package org.teamneko.schrodinger.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.teamneko.meowlib.InventoryItem;
import org.teamneko.meowlib.NamedProduct;
import org.teamneko.schrodinger.dao.InventoryDAO;

public class PostgresInventoryDAO implements InventoryDAO {
	private QueryRunner runner;

	public PostgresInventoryDAO(PostgresDatabase database) {
		runner = new QueryRunner(database.getDataSource());
	}

	private Integer getId(InventoryItem item) {
		try {
			return runner.query("SELECT id FROM inventory WHERE id_product=? AND id_box = ? LIMIT 1",
					new ResultSetHandler<Integer>() {

						@Override
						public Integer handle(ResultSet rs) throws SQLException {
							rs.next();
							return rs.getInt("id");
						}

					}, item.getIdProduct(), item.getIdBox());
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private void create(InventoryItem item) {
		try {
			runner.update("INSERT INTO inventory(id_product, id_box, quantity) VALUES (?, ?, ?)", item.getIdProduct(),
					item.getIdBox(), item.getQuantity());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void update(int id, int qty) {
		try {
			runner.update("UPDATE inventory SET quantity = quantity + ? WHERE id = ?", qty, id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addToInventory(InventoryItem item) {
		Integer id = getId(item);
		// If item does not exists
		if (id == null) {
			// Create it
			create(item);
		} else {
			// If it exists, update it
			update(id, item.getQuantity());
		}
	}

	@Override
	public void removeFromInventory(InventoryItem item) {
		Integer id = getId(item);
		// If item does not exists
		if (id == null) {
			// Create it
			item.setQuantity(0 - item.getQuantity());
			create(item);
		} else {
			// If it exists, update it
			update(id, 0 - item.getQuantity());
		}

	}

	@Override
	public List<NamedProduct> getBoxContents(int idBox) {
		try {
			return runner.query(
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return new ArrayList<NamedProduct>(0);
	}

	@Override
	public int getStock(int idProduct) {
		try {
			return runner.query("SELECT SUM(quantity) AS total FROM inventory WHERE id_product=?",
					new ResultSetHandler<Integer>() {
						@Override
						public Integer handle(ResultSet rs) throws SQLException {
							rs.next();
							return rs.getInt("total");
						}

					}, idProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

}
