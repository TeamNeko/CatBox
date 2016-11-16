package org.teamneko.meowlib.json;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductSearchResult extends SearchResult {
	private Product product;

	public ProductSearchResult() {
		super("product");
		this.product = new Product();
	}

	public ProductSearchResult(Product product) {
		super("product");
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "ProductSearchResult [product=" + product + ", type=" + getType() + "]";
	}
	
}
