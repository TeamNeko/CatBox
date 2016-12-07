package org.teamneko.schrodinger.dao;

/**
 * A factory for creating AbstractDAO objects.
 */
public abstract class AbstractDAOFactory {
	
	/**
	 * Gets the alerts DAO.
	 *
	 * @return the alerts DAO
	 */
	public abstract AlertsDAO getAlertsDAO();
	
	/**
	 * Gets the boxes DAO.
	 *
	 * @return the boxes DAO
	 */
	public abstract BoxesDAO getBoxesDAO();
	
	/**
	 * Gets the history DAO.
	 *
	 * @return the history DAO
	 */
	public abstract HistoryDAO getHistoryDAO();
	
	/**
	 * Gets the inventory DAO.
	 *
	 * @return the inventory DAO
	 */
	public abstract InventoryDAO getInventoryDAO();
	
	/**
	 * Gets the products DAO.
	 *
	 * @return the products DAO
	 */
	public abstract ProductsDAO getProductsDAO();
	
	/**
	 * Gets the transactions DAO.
	 *
	 * @return the transactions DAO
	 */
	public abstract TransactionsDAO getTransactionsDAO();
	
	/**
	 * Gets the users DAO.
	 *
	 * @return the users DAO
	 */
	public abstract UsersDAO getUsersDAO();
}
