package org.teamneko.schrodinger.dao;

import org.teamneko.meowlib.pojo.InventoryItem;

public interface InventoryDAO {
	public void addToInventory(InventoryItem item);
	public void removeFromInventory(InventoryItem item);
}
