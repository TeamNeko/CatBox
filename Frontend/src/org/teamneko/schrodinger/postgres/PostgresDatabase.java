package org.teamneko.schrodinger.postgres;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PostgresDatabase {
	private Connection connection;
	
	public PostgresDatabase(String connectionUrl) throws ClassNotFoundException, SQLException {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(connectionUrl);
	}
	
	public PostgresDatabase(String connectionUrl, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		connection = DriverManager.getConnection(connectionUrl, user, password);
}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return connection.createStatement().executeQuery(query);
	}
	
	public PreparedStatement prepare(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}
}
