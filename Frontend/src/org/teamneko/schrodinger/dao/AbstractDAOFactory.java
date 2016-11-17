package org.teamneko.schrodinger.dao;

public abstract class AbstractDAOFactory {
	public abstract AlertsDAO getAlertsDAO();
	public abstract BoxesDAO getBoxesDAO();
	public abstract HistoryDAO getHistoryDAO();
	public abstract InventoryDAO getInventoryDAO();
	public abstract ProductsDAO getProductsDAO();
	public abstract TransactionsDAO getTransactionsDAO();
	public abstract UsersDAO getUsersDAO();
}
