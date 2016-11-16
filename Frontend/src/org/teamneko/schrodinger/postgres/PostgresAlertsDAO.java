package org.teamneko.schrodinger.postgres;

import java.sql.SQLException;
import java.util.Optional;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.teamneko.meowlib.Alert;
import org.teamneko.schrodinger.dao.AlertsDAO;

public class PostgresAlertsDAO implements AlertsDAO {
	private QueryRunner runner;
	private ResultSetHandler<Alert> alertHandler = new BeanHandler<Alert>(Alert.class);

	public PostgresAlertsDAO(PostgresDatabase database) {
		runner = new QueryRunner(database.getDataSource());
	}

	@Override
	public Optional<Alert> getAlert(int idProduct) {
		try {
			return Optional
					.ofNullable(runner.query("SELECT * FROM alerts WHERE id_product=? LIMIT 1", alertHandler, idProduct));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}

	@Override
	public void changeLevel(int idProduct, int level) {
		try {
			runner.update("UPDATE alerts SET id_message=? WHERE id_product=?", level, idProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addAlert(int idProduct, int level) {
		try {
			runner.update("INSERT INTO alerts(id_product, id_message) VALUES (?, ?)", idProduct, level);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeAlert(int idProduct) {
		try {
			runner.update("DELETE FROM alerts WHERE id_product=?", idProduct);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
