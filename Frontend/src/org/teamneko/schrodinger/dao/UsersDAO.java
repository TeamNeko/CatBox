package org.teamneko.schrodinger.dao;

import java.util.Optional;

import org.teamneko.meowlib.json.User;

public interface UsersDAO {
	public Optional<User> search(String number);	
}
