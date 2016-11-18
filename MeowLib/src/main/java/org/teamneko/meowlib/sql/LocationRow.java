package org.teamneko.meowlib.sql;

public class LocationRow {
	private int id;
	private int floor;
	private String row;
	private String shelf;
	private String shelf_level;
	private String pallet;
	private String building;

	public LocationRow() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBuilding() {
		return building;
	}

	public int getFloor() {
		return floor;
	}

	public String getPallet() {
		return pallet;
	}

	public String getRow() {
		return row;
	}

	public String getShelf() {
		return shelf;
	}

	public String getShelf_level() {
		return shelf_level;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public void setPallet(String pallet) {
		this.pallet = pallet;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public void setShelf_level(String shelf_level) {
		this.shelf_level = shelf_level;
	}

}
