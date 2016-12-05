package org.teamneko.schrodinger.postgres;

import javax.sql.DataSource;

import org.postgresql.ds.PGSimpleDataSource;

/**
 * The Class PostgresDatabase.
 */
public class PostgresDatabase {
	
	/** The source. */
	private PGSimpleDataSource source;

	/**
	 * Instantiates a new postgres database.
	 *
	 * @param serverName the server name
	 * @param databaseName the database name
	 * @param userName the user name
	 * @param password the password
	 */
	public PostgresDatabase(String serverName, String databaseName, String userName, String password) {
		source = new PGSimpleDataSource();
		source.setServerName(serverName);
		source.setDatabaseName(databaseName);
		source.setUser(userName);
		source.setPassword(password);
		source.setCurrentSchema("public");
	}

	/**
	 * Gets the data source.
	 *
	 * @return the data source
	 */
	public DataSource getDataSource() {
		return source;
	}
}
