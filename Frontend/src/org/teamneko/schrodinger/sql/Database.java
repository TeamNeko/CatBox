package org.teamneko.schrodinger.sql;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.teamneko.schrodinger.dao.AbstractDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDAOFactory;
import org.teamneko.schrodinger.postgres.PostgresDatabase;

public class Database implements ServletContextListener {
	private static AbstractDAOFactory factory;
	
    public void contextInitialized(ServletContextEvent event) {
        // Webapp startup.
    	try {
			factory = new PostgresDAOFactory(new PostgresDatabase("jdbc:postgresql://localhost/catbox", "jaune", "yolo"));
		} catch (ClassNotFoundException | SQLException e) {
			
		}
    }

    public static AbstractDAOFactory getDAOFactory() {
    	return factory;
    }
    
    public void contextDestroyed(ServletContextEvent event) {
        // Webapp shutdown.
    }

}