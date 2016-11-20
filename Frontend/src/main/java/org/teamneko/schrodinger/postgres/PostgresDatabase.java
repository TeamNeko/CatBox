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
		source.setCurrentSchema("public");
	}

	public DataSource getDataSource() {
		return source;
	}
}
