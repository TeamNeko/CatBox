package org.teamneko.schrodinger.dao;

import java.util.Optional;

import org.teamneko.meowlib.dto.Box;

public interface BoxesDAO {
	public boolean exists(String barcode);
	public void create(String barcode);
	public int getId(String barcode);
	public Optional<Box> search(String barcode);
}
