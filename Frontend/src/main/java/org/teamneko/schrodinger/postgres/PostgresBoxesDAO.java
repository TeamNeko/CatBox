package org.teamneko.schrodinger.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.dbutils.ResultSetHandler;
import org.teamneko.meowlib.json.Box;
import org.teamneko.schrodinger.dao.BoxesDAO;
import org.teamneko.schrodinger.sql.FilteredQueryRunner;

/**
 * The Class PostgresBoxesDAO.
 */
public class PostgresBoxesDAO implements BoxesDAO {
	
	/** The runner. */
	private FilteredQueryRunner runner;

	/**
	 * Instantiates a new postgres boxes DAO.
	 *
	 * @param database the database
	 */
	public PostgresBoxesDAO(PostgresDatabase database) {
		runner = new FilteredQueryRunner(database.getDataSource());
	}

	/*
	 * @see org.teamneko.schrodinger.dao.BoxesDAO#exists(java.lang.String)
	 */
	@Override
	public boolean exists(String barcode) {
		return runner.queryFiltered("SELECT exists(SELECT 1 FROM boxes WHERE barcode = ?)",
			new ResultSetHandler<Boolean>() {

				@Override
				public Boolean handle(ResultSet rs) throws SQLException {
					rs.next();
					return rs.getBoolean("exists");
				}

		}, barcode);
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.BoxesDAO#create(java.lang.String)
	 */
	@Override
	public void create(String barcode) {
		runner.updateFiltered("INSERT INTO boxes(barcode) VALUES (?)", barcode);
	}

	/*
	 * @see org.teamneko.schrodinger.dao.BoxesDAO#getId(java.lang.String)
	 */
	@Override
	public int getId(String barcode) {
		return runner.queryFiltered("SELECT id FROM boxes WHERE barcode = ?", new ResultSetHandler<Integer>() {

			@Override
			public Integer handle(ResultSet rs) throws SQLException {
				if(!rs.next())
					return -1;
				
				return rs.getInt("id");
			}

		}, barcode);
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.BoxesDAO#search(java.lang.String)
	 */
	@Override
	public Optional<Box> search(String barcode) {
		return Optional.ofNullable(runner.queryFiltered("SELECT * FROM boxes WHERE barcode = ?", new ResultSetHandler<Box>() {

			@Override
			public Box handle(ResultSet rs) throws SQLException {
				if(!rs.next())
					return null;

				Box box = new Box();
				box.setBarcode(barcode);
				box.setId(rs.getInt("id"));
				box.setWeight(rs.getFloat("weight"));
				box.setCreated(new Date(rs.getDate("creation_date").getTime()));
				box.setModified(new Date(rs.getDate("last_modified").getTime()));
				box.setSize(rs.getString("size"));

				return box;
			}

		}, barcode));
	}
}
