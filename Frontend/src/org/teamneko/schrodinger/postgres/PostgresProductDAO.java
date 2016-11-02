package org.teamneko.schrodinger.postgres;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.teamneko.meowlib.dto.Product;
import org.teamneko.schrodinger.dao.ProductsDAO;

public class PostgresProductDAO implements ProductsDAO {
	private PostgresDatabase database;
	
	public PostgresProductDAO(PostgresDatabase database) {
		this.database = database;
	}
	
	@Override
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		
		try {
			ResultSet rs = database.executeQuery("SELECT * FROM \"Products\"");
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setAdded(rs.getDate("date_added"));
				product.setRemoved(rs.getDate("date_retired"));
				product.setWeight(rs.getDouble("weight"));
				products.add(product);
			}
		} catch(SQLException e) {	
		}
		
		return products;
	}

	@Override
	public Optional<Product> search(String barcode) {
		try {
			PreparedStatement ps = database.prepare("SELECT * FROM \"Products\" WHERE barcode=?");
			ps.setString(1, barcode);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setDescription(rs.getString("description"));
				product.setAdded(rs.getDate("date_added"));
				product.setRemoved(rs.getDate("date_retired"));
				product.setWeight(rs.getDouble("weight"));
				return Optional.of(product);
			}
		} catch(SQLException e) {	
		}
		
		return Optional.empty();
	}

}
