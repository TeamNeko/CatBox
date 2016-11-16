package org.teamneko.schrodinger.dao;

import java.util.List;

import org.teamneko.meowlib.InventoryItem;
import org.teamneko.meowlib.NamedProduct;

public interface InventoryDAO {
	public void addToInventory(InventoryItem item);
	public void removeFromInventory(InventoryItem item);
	public List<NamedProduct> getBoxContents(int idBox);
	public int getStock(int idProduct);
}
