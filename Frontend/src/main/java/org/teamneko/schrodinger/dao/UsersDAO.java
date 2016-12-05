package org.teamneko.schrodinger.dao;

import java.util.Optional;

import org.teamneko.meowlib.json.User;

/**
 * The Interface UsersDAO.
 */
public interface UsersDAO {
	
	/**
	 * Search user with user id.
	 *
	 * @param number the number
	 * @return the optional
	 */
	public Optional<User> search(String number);	
}
