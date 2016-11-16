package org.teamneko.schrodinger.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.teamneko.meowlib.Alert;
import org.teamneko.meowlib.InventoryItem;
import org.teamneko.meowlib.NamedProduct;
import org.teamneko.schrodinger.dao.InventoryDAO;

public class PostgresInventoryDAO implements InventoryDAO {
	private QueryRunner runner;
	private ResultSetHandler<InventoryItem> itemHandler = new BeanHandler<InventoryItem>(InventoryItem.class);


	public PostgresInventoryDAO(PostgresDatabase database) {
		runner = new QueryRunner(database.getDataSource());
	}
	
	public Optional<InventoryItem> get(int idBox, int idProduct) {
		try {
			return Optional.ofNullable(runner.query("SELECT * FROM inventory WHERE id_product=? AND id_box = ? LIMIT 1", itemHandler, idProduct, idBox));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
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

	@Override
	public void update(InventoryItem item) {
		try {
			runner.update("UPDATE inventory SET quantity = ? WHERE id = ?", item.getQuantity(), item.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(InventoryItem item) {
		try {
			runner.update("INSERT INTO inventory(id_product, id_box, quantity) VALUES (?, ?, ?)", item.getId_product(), item.getId_box(), item.getQuantity());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(InventoryItem item) {
		try {
			runner.update("DELETE FROM inventory WHERE id=?", item.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
