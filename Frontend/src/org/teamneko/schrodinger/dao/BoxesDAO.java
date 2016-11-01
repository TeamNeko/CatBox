package org.teamneko.schrodinger.dao;

public interface BoxesDAO {
	public boolean exists(String number);
	public void create(String barcode);
}
