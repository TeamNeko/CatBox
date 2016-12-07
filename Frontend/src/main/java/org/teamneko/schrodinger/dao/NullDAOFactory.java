package org.teamneko.schrodinger.dao;

/**
 * A factory for creating NullDAO objects.
 */
public class NullDAOFactory extends AbstractDAOFactory {

	/* 
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getAlertsDAO()
	 */
	@Override
	public AlertsDAO getAlertsDAO() {
		return null;
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getBoxesDAO()
	 */
	@Override
	public BoxesDAO getBoxesDAO() {
		return null;
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getHistoryDAO()
	 */
	@Override
	public HistoryDAO getHistoryDAO() {
		return null;
	}

	/*
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getInventoryDAO()
	 */
	@Override
	public InventoryDAO getInventoryDAO() {
		return null;
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getProductsDAO()
	 */
	@Override
	public ProductsDAO getProductsDAO() {
		return null;
	}

	/*
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getTransactionsDAO()
	 */
	@Override
	public TransactionsDAO getTransactionsDAO() {
		return null;
	}

	/* 
	 * @see org.teamneko.schrodinger.dao.AbstractDAOFactory#getUsersDAO()
	 */
	@Override
	public UsersDAO getUsersDAO() {
		return null;
	}

}
