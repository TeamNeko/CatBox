package postgres.meowlib.teamneko.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private Connection connection;
	
	public Database(String connectionUrl) throws ClassNotFoundException, SQLException {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(connectionUrl);
	}
}
