package org.teamneko.schrodinger.postgres;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.teamneko.meowlib.pojo.Box;
import org.teamneko.schrodinger.dao.BoxesDAO;

public class PostgresBoxesDAO implements BoxesDAO {
	private final PostgresDatabase database;
	
	public PostgresBoxesDAO(PostgresDatabase database) {
		this.database = database;
	}
	@Override
	public boolean exists(String barcode) {
		try {
			PreparedStatement ps = database.prepare("SELECT exists(SELECT 1 FROM \"Boxes\" WHERE barcode = ?)");
			ps.setString(1, barcode);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getBoolean("exists");
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public void create(String barcode) {
		try {
			PreparedStatement ps = database.prepare("INSERT INTO \"Boxes\"(barcode) VALUES (?)");
			ps.setString(1, barcode);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
