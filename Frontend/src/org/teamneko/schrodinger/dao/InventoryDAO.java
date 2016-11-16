package org.teamneko.schrodinger.dao;

import java.util.List;
import java.util.Optional;

import org.teamneko.meowlib.json.NamedProduct;
import org.teamneko.meowlib.sql.InventoryRow;

public interface InventoryDAO {
	public void update(InventoryRow item);
	public void insert(InventoryRow item);
	public void delete(InventoryRow item);
	public Optional<InventoryRow> get(int idBox, int idProduct);
	public List<NamedProduct> getBoxContents(int idBox);
	public int getStock(int idProduct);
}
