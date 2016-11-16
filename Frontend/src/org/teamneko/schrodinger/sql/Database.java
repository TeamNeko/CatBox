package org.teamneko.schrodinger.sql;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.teamneko.schrodinger.dao.AbstractDAOFactory;
import org.teamneko.schrodinger.dao.NullDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDatabase;

public class Database implements ServletContextListener {
	private static final String url = "elmer.db.elephantsql.com";
	private static final int port = 5432;
	private static final String database = "jmtntlek";
	private static final String user = "jmtntlek";
	private static final String password = "vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI";

	private static AbstractDAOFactory factory = new NullDAOFactory();

    public void contextInitialized(ServletContextEvent event) {
		factory = new PostgresDAOFactory(new PostgresDatabase(url, database, user, password));
    }

    public static AbstractDAOFactory getDAOFactory() {
    	return factory;
    }

    public void contextDestroyed(ServletContextEvent event) {
        // Webapp shutdown.
    }

}