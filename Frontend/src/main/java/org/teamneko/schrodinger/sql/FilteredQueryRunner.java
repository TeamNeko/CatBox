package org.teamneko.schrodinger.sql;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * Query Runner that filters SQL exception and prints them on the error output.
 *
 * @author Tommy Savaria
 */
public class FilteredQueryRunner extends QueryRunner {
	
	/**
	 * Instantiates a new filtered query runner.
	 *
	 * @param ds the ds
	 */
	public FilteredQueryRunner(DataSource ds) {
		super(ds);
	}

	/**
	 * Query filtered.
	 *
	 * @param <T> the generic type
	 * @param sql the sql
	 * @param rsh the rsh
	 * @param params the params
	 * @return the t
	 */
	public <T> T queryFiltered(String sql, ResultSetHandler<T> rsh, Object... params) {
		try {
			return query(sql, rsh, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Update filtered.
	 *
	 * @param sql the sql
	 * @param params the params
	 * @return the int
	 */
	public int updateFiltered(String sql, Object... params) {
		try {
			return update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
