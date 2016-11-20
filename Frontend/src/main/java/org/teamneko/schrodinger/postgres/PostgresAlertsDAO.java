package org.teamneko.schrodinger.postgres;

import java.util.Optional;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.teamneko.meowlib.sql.AlertRow;
import org.teamneko.schrodinger.dao.AlertsDAO;
import org.teamneko.schrodinger.sql.FilteredQueryRunner;

public class PostgresAlertsDAO implements AlertsDAO {
	private FilteredQueryRunner runner;
	private ResultSetHandler<AlertRow> alertHandler = new BeanHandler<AlertRow>(AlertRow.class);

	public PostgresAlertsDAO(PostgresDatabase database) {
		runner = new FilteredQueryRunner(database.getDataSource());
	}

	@Override
	public Optional<AlertRow> getAlert(int idProduct) {
		return Optional
					.ofNullable(runner.queryFiltered("SELECT * FROM alerts WHERE id_product=? LIMIT 1", alertHandler, idProduct));
	}

	@Override
	public void changeLevel(int idProduct, int level) {
		runner.updateFiltered("UPDATE alerts SET id_message=? WHERE id_product=?", level, idProduct);
	}

	@Override
	public void addAlert(int idProduct, int level) {
		runner.updateFiltered("INSERT INTO alerts(id_product, id_message) VALUES (?, ?)", idProduct, level);
	}

	@Override
	public void removeAlert(int idProduct) {
		runner.updateFiltered("DELETE FROM alerts WHERE id_product=?", idProduct);
	}

}
