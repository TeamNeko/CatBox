package org.teamneko.schrodinger.dao;

public interface BoxesDAO {
	public boolean exists(String barcode);
	public void create(String barcode);
	public int getId(String barcode);
}
