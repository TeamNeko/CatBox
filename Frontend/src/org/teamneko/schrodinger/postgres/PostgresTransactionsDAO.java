package org.teamneko.schrodinger.postgres;

import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.commons.dbutils.QueryRunner;
import org.teamneko.meowlib.Transaction;
import org.teamneko.schrodinger.dao.TransactionsDAO;

public class PostgresTransactionsDAO implements TransactionsDAO {
	private QueryRunner runner;

	public PostgresTransactionsDAO(PostgresDatabase database) {
		runner = new QueryRunner(database.getDataSource());
	}

	@Override
	public void addTransaction(Transaction t) {
		try {
			runner.update(
					"INSERT INTO transactions(id_user, id_product, id_box, time, type, quantity) VALUES (?, ?, ?, ?, ?, ?)",
					t.getIdUser(), t.getIdProduct(), t.getIdBox(), new Timestamp(t.getTime()), t.getType(),
					t.getQuantity());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
