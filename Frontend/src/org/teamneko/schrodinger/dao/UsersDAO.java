package org.teamneko.schrodinger.dao;

import java.util.Optional;

import org.teamneko.meowlib.dto.User;

public interface UsersDAO {
	public Optional<User> search(String number);	
}
