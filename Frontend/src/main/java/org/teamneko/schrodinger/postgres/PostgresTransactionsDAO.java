package org.teamneko.schrodinger.postgres;

import java.sql.Timestamp;

import org.teamneko.meowlib.json.Transaction;
import org.teamneko.schrodinger.dao.TransactionsDAO;
import org.teamneko.schrodinger.sql.FilteredQueryRunner;

public class PostgresTransactionsDAO implements TransactionsDAO {
	private FilteredQueryRunner runner;

	public PostgresTransactionsDAO(PostgresDatabase database) {
		runner = new FilteredQueryRunner(database.getDataSource());
	}

	@Override
	public void addTransaction(Transaction t) {
		runner.updateFiltered(
					"INSERT INTO transactions(id_user, id_product, id_box, time, type, quantity) VALUES (?, ?, ?, ?, ?, ?)",
					t.getIdUser(), t.getIdProduct(), t.getIdBox(), new Timestamp(t.getTime()), t.getType(),
					t.getQuantity());
	}

}
