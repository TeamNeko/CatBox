package org.teamneko.schrodinger.postgres;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.teamneko.meowlib.sql.HistoryRow;
import org.teamneko.schrodinger.dao.HistoryDAO;
import org.teamneko.schrodinger.sql.FilteredQueryRunner;

public class PostgresHistoryDAO implements HistoryDAO {
	private FilteredQueryRunner runner;

	public PostgresHistoryDAO(PostgresDatabase database) {
		runner = new FilteredQueryRunner(database.getDataSource());
	}
	
	@Override
	public void add(HistoryRow item) {
		runner.updateFiltered("INSERT INTO history(id_product, quantity, time) VALUES(?, ?, ?)", item.getId_product(), item.getQuantity(), new Timestamp(item.getTime().getTime()));
	}
	
	@Override
	public List<HistoryRow> getFullHistory(int idProduct) {
		return runner.queryFiltered("SELECT * FROM history WHERE id_product=?", new BeanListHandler<HistoryRow>(HistoryRow.class), idProduct);
	}

}
