package postgres.meowlib.teamneko.org;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresDatabase {
	private Connection connection;
	
	public PostgresDatabase(String connectionUrl) throws ClassNotFoundException, SQLException {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(connectionUrl);
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return connection.createStatement().executeQuery(query);
	}
}
