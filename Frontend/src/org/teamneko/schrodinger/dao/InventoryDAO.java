package org.teamneko.schrodinger.dao;

import java.util.List;
import java.util.Optional;

import org.teamneko.meowlib.InventoryItem;
import org.teamneko.meowlib.NamedProduct;

public interface InventoryDAO {
	public void update(InventoryItem item);
	public void insert(InventoryItem item);
	public void delete(InventoryItem item);
	public Optional<InventoryItem> get(int idBox, int idProduct);
	public List<NamedProduct> getBoxContents(int idBox);
	public int getStock(int idProduct);
}
