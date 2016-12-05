package org.teamneko.schrodinger.sql;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.teamneko.schrodinger.dao.AbstractDAOFactory;
import org.teamneko.schrodinger.dao.NullDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDatabase;

/**
 * The Class Database.
 */
public class Database implements ServletContextListener {
	
	/** The Constant url. */
	private static final String url = "elmer.db.elephantsql.com";
	
	/** The Constant database. */
	//private static final int port = 5432;
	private static final String database = "jmtntlek";
	
	/** The Constant user. */
	private static final String user = "jmtntlek";
	
	/** The Constant password. */
	private static final String password = "vaYxsY1WBNr5gYMMd-74kLrc98gqNhqI";

	/** The factory. */
	private static AbstractDAOFactory factory = new NullDAOFactory();

    /* 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event) {
		factory = new PostgresDAOFactory(new PostgresDatabase(url, database, user, password));
    }

    /**
     * Gets the DAO factory.
     *
     * @return the DAO factory
     */
    public static AbstractDAOFactory getDAOFactory() {
    	return factory;
    }

    /*
     * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent event) {
        // Webapp shutdown.
    }

}