package org.teamneko.schrodinger.postgres;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Optional;

import org.teamneko.meowlib.dto.Box;
import org.teamneko.schrodinger.dao.BoxesDAO;

public class PostgresBoxesDAO implements BoxesDAO {
	private final PostgresDatabase database;
	
	public PostgresBoxesDAO(PostgresDatabase database) {
		this.database = database;
	}
	@Override
	public boolean exists(String barcode) {
		try {
			PreparedStatement ps = database.prepare("SELECT exists(SELECT 1 FROM boxes WHERE barcode = ?)");
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
			PreparedStatement ps = database.prepare("INSERT INTO boxes(barcode) VALUES (?)");
			ps.setString(1, barcode);
			ps.executeUpdate();
		} catch (SQLException e) {
		}
	}
	@Override
	public int getId(String barcode) {
		PreparedStatement ps;
		try {
			ps = database.prepare("SELECT id FROM boxes WHERE barcode = ?");
			ps.setString(1, barcode);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt("id");
		} catch (SQLException e) {
			return -1;
		}
	}
	@Override
	public Optional<Box> search(String barcode) {
		PreparedStatement ps;
		try {
			ps = database.prepare("SELECT * FROM boxes WHERE barcode = ?");
			ps.setString(1, barcode);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Box box = new Box();
				box.setBarcode(barcode);
				box.setId(rs.getInt("id"));
				box.setWeight(rs.getFloat("weight"));
				box.setCreated(new Date(rs.getDate("creation_date").getTime()));
				box.setModified(new Date(rs.getDate("last_modified").getTime()));
				box.setSize(rs.getString("size"));
				return Optional.of(box);
			}
		} catch (SQLException e) {
		}
		
		return Optional.empty();
	}
}
