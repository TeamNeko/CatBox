package org.teamneko.schrodinger.dao;

public class NullDAOFactory extends AbstractDAOFactory {

	@Override
	public ProductsDAO getProductsDAO() {
		return null;
	}

	@Override
	public UsersDAO getUsersDAO() {
		return null;
	}

	@Override
	public BoxesDAO getBoxesDAO() {
		return null;
	}

	@Override
	public TransactionsDAO getTransactionsDAO() {
		return null;
	}

	@Override
	public InventoryDAO getInventoryDAO() {
		return null;
	}

}
