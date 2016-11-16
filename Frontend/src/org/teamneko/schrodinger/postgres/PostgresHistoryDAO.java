package org.teamneko.schrodinger.postgres;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.dbutils.QueryRunner;
import org.teamneko.meowlib.HistoryItem;
import org.teamneko.schrodinger.dao.HistoryDAO;

public class PostgresHistoryDAO implements HistoryDAO {
	private QueryRunner runner;

	public PostgresHistoryDAO(PostgresDatabase database) {
		runner = new QueryRunner(database.getDataSource());
	}
	@Override
	public void add(HistoryItem item) {
		try {
			runner.update("INSERT INTO history(id_product, quantity, time) VALUES(?, ?, ?)", item.getId_product(), item.getQuantity(), new Timestamp(item.getTime().getTime()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
