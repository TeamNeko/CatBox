package org.teamneko.schrodinger.dao;

import org.teamneko.meowlib.json.Transaction;

/**
 * The Interface TransactionsDAO.
 */
public interface TransactionsDAO {
	
	/**
	 * Adds the transaction.
	 *
	 * @param t the transaction
	 */
	public void addTransaction(Transaction t);
}
