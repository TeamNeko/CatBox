package org.teamneko.schrodinger.postgres;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

public class PostgresDatabase {
	private PGSimpleDataSource source;

	public PostgresDatabase(String serverName, String databaseName, String userName, String password) {
		source = new PGSimpleDataSource();
		source.setServerName(serverName);
		source.setDatabaseName(databaseName);
		source.setUser(userName);
		source.setPassword(password);
	}

	public DataSource getDataSource() {
		return source;
	}

	/*
	 * public ResultSet executeQuery(String query) throws SQLException { return
	 * source.getConnection().createStatement().executeQuery(query); }
	 * 
	 * public PreparedStatement prepare(String sql) throws SQLException { return
	 * source.getConnection().prepareStatement(sql); }
	 */
}
