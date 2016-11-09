package org.teamneko.schrodinger.dao;

import java.util.List;

import org.teamneko.meowlib.dto.NamedProduct;
import org.teamneko.meowlib.pojo.InventoryItem;

public interface InventoryDAO {
	public void addToInventory(InventoryItem item);
	public void removeFromInventory(InventoryItem item);
	public List<NamedProduct> getBoxContents(int idBox);
}
