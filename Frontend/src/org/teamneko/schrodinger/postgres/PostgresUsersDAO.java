package org.teamneko.schrodinger.postgres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.teamneko.meowlib.User;
import org.teamneko.schrodinger.dao.UsersDAO;

public class PostgresUsersDAO implements UsersDAO {
	private QueryRunner runner;

	public PostgresUsersDAO(PostgresDatabase database) {
		runner = new QueryRunner(database.getDataSource());
	}

	@Override
	public Optional<User> search(String number) {
		try {
			return Optional.of(runner.query("SELECT * FROM users WHERE number=?", new ResultSetHandler<User>() {

				@Override
				public User handle(ResultSet rs) throws SQLException {
					// Create User Object
					rs.next();

					User resultUser = new User();
					resultUser.setId(rs.getInt("id"));
					resultUser.setFirstName(rs.getString("first_name"));
					resultUser.setLastName(rs.getString("last_name"));
					resultUser.setNumber(number);
					resultUser.setType(rs.getString("type"));

					return resultUser;
				}

			}, number));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Optional.empty();
	}
}
