package org.teamneko.schrodinger.postgres;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.teamneko.schrodinger.dao.UsersDAO;
import org.teamneko.meowlib.dto.User;

public class PostgresUsersDAO implements UsersDAO {
private PostgresDatabase database;
	
	public PostgresUsersDAO(PostgresDatabase database) {
		this.database = database;
	}
	
	@Override
	public Optional<User> getUser(String number) {
		try {
			//Prepare Statement
			PreparedStatement ps = database.prepare("SELECT * FROM \"Users\" WHERE number=?");
			ps.setString(1, number);
			
			//Execute
			ResultSet rs = ps.executeQuery();
			
			if(!rs.next())
				//We did not find a user with this number
				//Return an empty object
				return Optional.empty();
			
			//Return a new instance of user
			return Optional.of(new User(rs.getInt("idUser"), rs.getString("first_name"), rs.getString("last_name"), number, rs.getString("type")));
			
		} catch (SQLException e) {
			//Return an empty object
			return Optional.empty();
		}
	}

}
