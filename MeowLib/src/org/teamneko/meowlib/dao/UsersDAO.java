package org.teamneko.meowlib.dao;

import java.util.Optional;

import org.teamneko.meowlib.obj.User;

public interface UsersDAO {
	public Optional<User> getUser(String number);	
}
