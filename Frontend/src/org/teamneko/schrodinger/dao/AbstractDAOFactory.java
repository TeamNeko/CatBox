package org.teamneko.schrodinger.dao;

public abstract class AbstractDAOFactory {
	public abstract ProductsDAO getProductsDAO();
	public abstract UsersDAO getUsersDAO();
	public abstract BoxesDAO getBoxesDAO();
}
