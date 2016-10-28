package org.teamneko.meowlib.dao;

public abstract class AbstractDAOFactory {
	public abstract ProductsDAO getProductsDAO();
	public abstract UsersDAO getUsersDAO();
}
