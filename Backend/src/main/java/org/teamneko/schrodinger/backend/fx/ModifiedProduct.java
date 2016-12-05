package org.teamneko.schrodinger.backend.fx;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


/**
 * The Class ModifiedProduct.
 */
public class ModifiedProduct {
	
	/** The id. */
	private IntegerProperty  id;
	
	/** The quantity. */
	private IntegerProperty  quantity;
	
	/** The name. */
	private StringProperty  name;
	
	/** The modified quantity. */
	private StringProperty  modifiedQty;
	
	/**
	 * Instantiates a new modified product.
	 *
	 * @param id the id
	 * @param quantity the quantity
	 * @param name the name
	 */
	public ModifiedProduct(int id, int quantity, String name) {
		idProperty().set(id);
		quantityProperty().set(quantity);
		nameProperty().set(name);
		setModifiedqty(0);
	}	
	
	/**
	 * Instantiates a new modified product.
	 *
	 * @param id the id
	 * @param quantity the quantity
	 * @param name the name
	 * @param modifiedQty the modified quantity
	 */
	public ModifiedProduct(int id, int quantity, String name, int modifiedQty) {
		idProperty().set(id);
		quantityProperty().set(quantity);
		nameProperty().set(name);
		setModifiedqty(modifiedQty);
	}

	/**
	 * Id property.
	 *
	 * @return the integer property
	 */
	public IntegerProperty idProperty() {
		if (id == null) id = new SimpleIntegerProperty(this, "0");
        return id; 
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return idProperty().get();
	}
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		idProperty().set(id);
	}
	
	/**
	 * Quantity property.
	 *
	 * @return the integer property
	 */
	public IntegerProperty quantityProperty() {
		if (quantity == null) quantity = new SimpleIntegerProperty(this, "0");
        return quantity; 
	}
	
	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantityProperty().get();
	}
	
	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(int quantity) {
		quantityProperty().set(quantity);
	}
	
	/**
	 * Name property.
	 *
	 * @return the string property
	 */
	public StringProperty nameProperty() {
		if (name == null) name = new SimpleStringProperty(this, "0");
        return name; 
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return nameProperty().get();
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		nameProperty().set(name);
	}
	
	/**
	 * Modifiedqty property.
	 *
	 * @return the string property
	 */
	public StringProperty modifiedqtyProperty() {
		if (modifiedQty == null) modifiedQty = new SimpleStringProperty(this, "0");
        return modifiedQty; 
	}
	
	/**
	 * Gets the modifiedqty.
	 *
	 * @return the modifiedqty
	 */
	public int getModifiedqty() {
		return Integer.parseInt(modifiedqtyProperty().get().replace("+", ""));
	}
	
	/**
	 * Sets the modifiedqty.
	 *
	 * @param quantity the new modifiedqty
	 */
	public void setModifiedqty(int quantity) {
		String newQty = "";
		if(quantity>0)
			newQty = "+" + quantity;
		else
			newQty = Integer.toString(quantity);
		modifiedqtyProperty().set(newQty);
	}
}
