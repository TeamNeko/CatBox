package org.teamneko.schrodinger.postgres;

import java.util.Optional;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.teamneko.meowlib.sql.AlertRow;
import org.teamneko.schrodinger.dao.AlertsDAO;
import org.teamneko.schrodinger.sql.FilteredQueryRunner;

/**
 * The Class PostgresAlertsDAO.
 */
public class PostgresAlertsDAO implements AlertsDAO {
	
	/** The runner. */
	private FilteredQueryRunner runner;
	
	/** The alert handler. */
	private ResultSetHandler<AlertRow> alertHandler = new BeanHandler<AlertRow>(AlertRow.class);

	/**
	 * Instantiates a new postgres alerts DAO.
	 *
	 * @param database the database
	 */
	public PostgresAlertsDAO(PostgresDatabase database) {
		runner = new FilteredQueryRunner(database.getDataSource());
	}

	/*
	 * @see org.teamneko.schrodinger.dao.AlertsDAO#getAlert(int)
	 */
	@Override
	public Optional<AlertRow> getAlert(int idProduct) {
		return Optional
					.ofNullable(runner.queryFiltered("SELECT * FROM alerts WHERE id_product=? LIMIT 1", alertHandler, idProduct));
	}

	/*
	 * @see org.teamneko.schrodinger.dao.AlertsDAO#changeLevel(int, int)
	 */
	@Override
	public void changeLevel(int idProduct, int level) {
		runner.updateFiltered("UPDATE alerts SET id_message=? WHERE id_product=?", level, idProduct);
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.AlertsDAO#addAlert(int, int)
	 */
	@Override
	public void addAlert(int idProduct, int level) {
		runner.updateFiltered("INSERT INTO alerts(id_product, id_message) VALUES (?, ?)", idProduct, level);
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.AlertsDAO#removeAlert(int)
	 */
	@Override
	public void removeAlert(int idProduct) {
		runner.updateFiltered("DELETE FROM alerts WHERE id_product=?", idProduct);
	}

}
