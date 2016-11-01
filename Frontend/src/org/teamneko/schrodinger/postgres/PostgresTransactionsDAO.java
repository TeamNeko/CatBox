package org.teamneko.schrodinger.postgres;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.teamneko.meowlib.pojo.Transaction;
import org.teamneko.schrodinger.dao.TransactionsDAO;

public class PostgresTransactionsDAO implements TransactionsDAO {
	PostgresDatabase database;
	
	public PostgresTransactionsDAO(PostgresDatabase database) {
		this.database = database;
	}

	@Override
	public void addTransaction(Transaction t) {
		try {
			PreparedStatement ps = database.prepare("INSERT INTO transactions(id_user, id_product, id_box, time, type, quantity) VALUES (?, ?, ?, ?, ?, ?)");
			
			ps.setInt(1, t.getIdUser());
			ps.setInt(2, t.getIdProduct());
			ps.setInt(3, t.getIdBox());
			ps.setTimestamp(4, new Timestamp(t.getTime()));
			ps.setString(5, t.getType());
			ps.setInt(6, t.getQuantity());
			
			ps.executeUpdate();
		} catch (SQLException e) {
		}
	}

}
