package dao.meowlib.teamneko.org;

public class InventoryItemDAO {
	private ProductDAO product;
	private BoxDAO box;
	private int quantity;

	public InventoryItemDAO() {
	}

	public ProductDAO getProduct() {
		return product;
	}

	public void setProduct(ProductDAO product) {
		this.product = product;
	}

	public BoxDAO getBox() {
		return box;
	}

	public void setBox(BoxDAO box) {
		this.box = box;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
