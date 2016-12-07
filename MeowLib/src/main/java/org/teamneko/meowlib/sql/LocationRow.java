package org.teamneko.meowlib.sql;

/**
 * The Class LocationRow.
 */
public class LocationRow {
	
	/** The id. */
	private int id;
	
	/** The floor. */
	private int floor;
	
	/** The row. */
	private String row;
	
	/** The shelf. */
	private String shelf;
	
	/** The shelf level. */
	private String shelf_level;
	
	/** The pallet. */
	private String pallet;
	
	/** The building. */
	private String building;

	/**
	 * Instantiates a new location row.
	 */
	public LocationRow() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the building.
	 *
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}

	/**
	 * Gets the floor.
	 *
	 * @return the floor
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * Gets the pallet.
	 *
	 * @return the pallet
	 */
	public String getPallet() {
		return pallet;
	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public String getRow() {
		return row;
	}

	/**
	 * Gets the shelf.
	 *
	 * @return the shelf
	 */
	public String getShelf() {
		return shelf;
	}

	/**
	 * Gets the shelf level.
	 *
	 * @return the shelf level
	 */
	public String getShelf_level() {
		return shelf_level;
	}

	/**
	 * Sets the building.
	 *
	 * @param building the new building
	 */
	public void setBuilding(String building) {
		this.building = building;
	}

	/**
	 * Sets the floor.
	 *
	 * @param floor the new floor
	 */
	public void setFloor(int floor) {
		this.floor = floor;
	}

	/**
	 * Sets the pallet.
	 *
	 * @param pallet the new pallet
	 */
	public void setPallet(String pallet) {
		this.pallet = pallet;
	}

	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(String row) {
		this.row = row;
	}

	/**
	 * Sets the shelf.
	 *
	 * @param shelf the new shelf
	 */
	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	/**
	 * Sets the shelf level.
	 *
	 * @param shelf_level the new shelf level
	 */
	public void setShelf_level(String shelf_level) {
		this.shelf_level = shelf_level;
	}

}
