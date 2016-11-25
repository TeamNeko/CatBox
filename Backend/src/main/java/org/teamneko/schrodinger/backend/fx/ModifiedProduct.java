package org.teamneko.schrodinger.backend.fx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ModifiedProduct {
	private IntegerProperty  id;
	private IntegerProperty  quantity;
	private StringProperty  name;
	private StringProperty  modifiedQty;
	
	public ModifiedProduct(int id, int quantity, String name) {
		idProperty().set(id);
		quantityProperty().set(quantity);
		nameProperty().set(name);
		modifiedqtyProperty().set("0");
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
	
	public IntegerProperty quantityProperty() {
		if (quantity == null) quantity = new SimpleIntegerProperty(this, "0");
        return quantity; 
	}
	
	public int getQuantity() {
		return quantityProperty().get();
	}
	
	public void setQuantity(int quantity) {
		quantityProperty().set(quantity);
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
	
	public StringProperty modifiedqtyProperty() {
		if (modifiedQty == null) modifiedQty = new SimpleStringProperty(this, "0");
        return modifiedQty; 
	}
	
	public int getModifiedqty() {
		return Integer.parseInt(modifiedqtyProperty().get().replace("+", ""));
	}
	
	public void setModifiedqty(int quantity) {
		String newQty = "";
		if(quantity>0)
			newQty = "+" + quantity;
		else
			newQty = Integer.toString(quantity);
		modifiedqtyProperty().set(newQty);
	}
}
