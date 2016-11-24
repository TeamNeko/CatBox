package org.teamneko.schrodinger.backend.fx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModifiedProduct {
	private IntegerProperty  id;
	private IntegerProperty  quantity;
	private StringProperty  name;
	
	public ModifiedProduct(int id, int quantity, String name) {
		idProperty().set(id);
		qtyProperty().set(quantity);
		nameProperty().set(name);
	}	

	public IntegerProperty idProperty() {
		if (id == null) id = new SimpleIntegerProperty(this, "0");
        return id; 
	}
	
	public int getId() {
		return idProperty().get();
	}
	
	public void setId(int id) {
		idProperty().set(id);
	}
	
	public IntegerProperty qtyProperty() {
		if (quantity == null) quantity = new SimpleIntegerProperty(this, "0");
        return quantity; 
	}
	
	public int getQty() {
		return qtyProperty().get();
	}
	
	public void setQty(int quantity) {
		qtyProperty().set(quantity);
	}
	
	public StringProperty nameProperty() {
		if (name == null) name = new SimpleStringProperty(this, "0");
        return name; 
	}
	
	public String getName() {
		return nameProperty().get();
	}
	
	public void setName(String name) {
		nameProperty().set(name);
	}
}
