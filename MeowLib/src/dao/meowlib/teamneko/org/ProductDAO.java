package dao.meowlib.teamneko.org;

import java.util.Date;

public class ProductDAO {
	private int id;
	private String name;
	private String description;
	private Date added;
	private Date removed;
	private float weight;

	public ProductDAO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getAdded() {
		return added;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public Date getRemoved() {
		return removed;
	}

	public float getWeight() {
		return weight;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRemoved(Date removed) {
		this.removed = removed;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}
