package org.teamneko.schrodinger.postgres;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.teamneko.meowlib.dto.NamedProduct;
import org.teamneko.meowlib.pojo.InventoryItem;
import org.teamneko.schrodinger.dao.InventoryDAO;

public class PostgresInventoryDAO implements InventoryDAO {
	PostgresDatabase database;
	
	public PostgresInventoryDAO(PostgresDatabase database) {
		this.database = database;
	}

	private Integer getId(InventoryItem item)
	{
		Integer id = null;
		
		try {
			PreparedStatement ps = database.prepare("SELECT id FROM \"Inventory\" WHERE \"idProduct\"=? AND \"idBox\" = ? LIMIT 1");
			ps.setInt(1, item.getIdProduct());
			ps.setInt(2, item.getIdBox());
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				id = rs.getInt("id");
			}
			
			ps.close();
		} catch (SQLException e) {
		}
		
		return id;
	}
	
	private void create(InventoryItem item) {
		try {
			PreparedStatement ps = database.prepare("INSERT INTO \"Inventory\"(\"idProduct\", \"idBox\", quantity) VALUES (?, ?, ?)");
			ps.setInt(1, item.getIdProduct());
			ps.setInt(2, item.getIdBox());
			ps.setInt(3, item.getQuantity());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
		}
	}
	
	private void update(int id, int qty) {
		try {
			PreparedStatement ps = database.prepare("UPDATE \"Inventory\" SET quantity = quantity + ? WHERE id = ?");
			ps.setInt(1, qty);
			ps.setInt(2, id);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
		}
	}
	
	@Override
	public void addToInventory(InventoryItem item) {
		Integer id = getId(item);
		//If item does not exists
		if(id == null) {
			//Create it
			create(item);
		} else {
			//If it exists, update it
			update(id, item.getQuantity());
		}
	}

	@Override
	public void removeFromInventory(InventoryItem item) {
		Integer id = getId(item);
		//If item does not exists
		if(id == null) {
			//Create it
			item.setQuantity(0-item.getQuantity());
			create(item);
		} else {
			//If it exists, update it
			update(id, 0-item.getQuantity());
		}

	}

	@Override
	public List<NamedProduct> getBoxContents(int idBox) {
		ArrayList<NamedProduct> products = new ArrayList<NamedProduct>();
		
		try {
			PreparedStatement ps = database.prepare("SELECT quantity, \"idProduct\", \"Products\".name FROM \"Inventory\" INNER JOIN \"Products\" ON \"Inventory\".\"idProduct\" = \"Products\".id WHERE \"Inventory\".\"idBox\" = ?");
			ps.setInt(1, idBox);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				NamedProduct product = new NamedProduct();
				product.setId(rs.getInt("idProduct"));
				product.setQuantity(rs.getInt("quantity"));
				product.setName(rs.getString("name"));
				products.add(product);
			}
			
			ps.close();
		} catch (SQLException e) {
		}
		
		return products;
	}

}
