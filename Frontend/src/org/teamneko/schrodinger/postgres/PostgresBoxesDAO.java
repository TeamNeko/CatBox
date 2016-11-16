package org.teamneko.schrodinger.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.teamneko.meowlib.Box;
import org.teamneko.schrodinger.dao.BoxesDAO;

public class PostgresBoxesDAO implements BoxesDAO {
	private QueryRunner runner;

	public PostgresBoxesDAO(PostgresDatabase database) {
		runner = new QueryRunner(database.getDataSource());
	}

	@Override
	public boolean exists(String barcode) {
		try {
			return runner.query("SELECT exists(SELECT 1 FROM boxes WHERE barcode = ?)",
					new ResultSetHandler<Boolean>() {

						@Override
						public Boolean handle(ResultSet rs) throws SQLException {
							rs.next();
							return rs.getBoolean("exists");
						}

					}, barcode);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void create(String barcode) {
		try {
			runner.update("INSERT INTO boxes(barcode) VALUES (?)", barcode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int getId(String barcode) {
		try {
			return runner.query("SELECT id FROM boxes WHERE barcode = ?", new ResultSetHandler<Integer>() {

				@Override
				public Integer handle(ResultSet rs) throws SQLException {
					rs.next();
					return rs.getInt("id");
				}

			}, barcode);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public Optional<Box> search(String barcode) {
		try {
			return Optional.ofNullable(runner.query("SELECT * FROM boxes WHERE barcode = ?", new ResultSetHandler<Box>() {

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
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}
}
