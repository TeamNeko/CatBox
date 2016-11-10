package org.teamneko.meowlib.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Transaction {
	private int idUser;
	private int idProduct;
	private int idBox;
	private int quantity;
	private long time;
	private String type;
	
	public Transaction() {
		this.idUser = -1;
		this.idProduct = -1;
		this.idBox = -1;
		this.quantity = -1;
		this.time = -1;
		this.type = "";
	}
	
	public Transaction(int idUser, int idProduct, int idBox, int quantity, long time, String type) {
		this.idUser = idUser;
		this.idProduct = idProduct;
		this.idBox = idBox;
		this.quantity = quantity;
		this.time = time;
		this.type = type;
	}
	public int getIdUser() {
		return idUser;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public int getIdBox() {
		return idBox;
	}
	public int getQuantity() {
		return quantity;
	}
	public long getTime() {
		return time;
	}
	public String getType() {
		return type;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public void setIdBox(int idBox) {
		this.idBox = idBox;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
