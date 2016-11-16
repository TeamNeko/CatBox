package org.teamneko.schrodinger.dao;

import java.util.Optional;

import org.teamneko.meowlib.Alert;

public interface AlertsDAO {
	public Optional<Alert> getAlert(int idProduct);
	public void changeLevel(int idProduct, int level);
	public void addAlert(int idProduct, int level);
	public void removeAlert(int idProduct);
}
