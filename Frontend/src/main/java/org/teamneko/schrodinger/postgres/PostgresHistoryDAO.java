package org.teamneko.schrodinger.postgres;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.teamneko.meowlib.sql.HistoryRow;
import org.teamneko.schrodinger.dao.HistoryDAO;

public class PostgresHistoryDAO implements HistoryDAO {
	private QueryRunner runner;

	public PostgresHistoryDAO(PostgresDatabase database) {
		runner = new QueryRunner(database.getDataSource());
	}
	
	@Override
	public void add(HistoryRow item) {
		try {
			runner.update("INSERT INTO history(id_product, quantity, time) VALUES(?, ?, ?)", item.getId_product(), item.getQuantity(), new Timestamp(item.getTime().getTime()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<HistoryRow> getFullHistory(int idProduct) {
		try {
			return runner.query("SELECT * FROM history WHERE id_product=?", new BeanListHandler<HistoryRow>(HistoryRow.class), idProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
