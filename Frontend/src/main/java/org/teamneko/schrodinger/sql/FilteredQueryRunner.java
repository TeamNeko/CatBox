package org.teamneko.schrodinger.sql;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

/**
 * Query Runner that filters SQL exception and prints them on the error output
 * @author Tommy Savaria
 *
 */
public class FilteredQueryRunner extends QueryRunner {
	public FilteredQueryRunner() {
		super();
	}

	public FilteredQueryRunner(boolean pmdKnownBroken) {
		super(pmdKnownBroken);
	}

	public FilteredQueryRunner(DataSource ds, boolean pmdKnownBroken) {
		super(ds, pmdKnownBroken);
	}

	public FilteredQueryRunner(DataSource ds) {
		super(ds);
	}

	public <T> T queryFiltered(String sql, ResultSetHandler<T> rsh, Object... params) {
		try {
			return query(sql, rsh, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public int updateFiltered(String sql, Object... params) {
		try {
			return update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
