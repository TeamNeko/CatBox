package org.teamneko.schrodinger.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.commons.dbutils.ResultSetHandler;
import org.teamneko.meowlib.json.User;
import org.teamneko.schrodinger.dao.UsersDAO;
import org.teamneko.schrodinger.sql.FilteredQueryRunner;

public class PostgresUsersDAO implements UsersDAO {
	private FilteredQueryRunner runner;

	public PostgresUsersDAO(PostgresDatabase database) {
		runner = new FilteredQueryRunner(database.getDataSource());
	}

	@Override
	public Optional<User> search(String number) {
		return Optional.ofNullable(runner.queryFiltered("SELECT * FROM users WHERE number=?", new ResultSetHandler<User>() {

			@Override
			public User handle(ResultSet rs) throws SQLException {
				// Create User Object
				if(!rs.next())
					return null;

				User resultUser = new User();
				resultUser.setId(rs.getInt("id"));
				resultUser.setFirstName(rs.getString("first_name"));
				resultUser.setLastName(rs.getString("last_name"));
				resultUser.setNumber(number);
				resultUser.setType(rs.getString("type"));

				return resultUser;
			}

		}, number));
	}
}
