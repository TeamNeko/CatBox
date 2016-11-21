package org.teamneko.schrodinger.postgres.test;

import org.teamneko.schrodinger.postgres.PostgresDatabase;

public class TestPostgresDatabase {
	private static final String url = "localhost";
	private static final String database = "catbox";
	private static final String user = "jaune";
	private static final String password = "yolo";

	private TestPostgresDatabase() {
		
	}
	
	public static PostgresDatabase get() {
		return new PostgresDatabase(url, database, user, password);
		
	}
}
