package org.teamneko.schrodinger.dao;

import java.util.List;

import org.teamneko.meowlib.sql.HistoryRow;

public interface HistoryDAO {
	public void add(HistoryRow item);
	public List<HistoryRow> getFullHistory(int idProduct);
}
