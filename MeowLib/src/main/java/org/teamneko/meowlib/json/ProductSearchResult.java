package org.teamneko.meowlib.json;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class ProductSearchResult.
 */
@XmlRootElement
public class ProductSearchResult extends SearchResult {
	
	/** The product. */
	private Product product;

	/**
	 * Instantiates a new product search result.
	 */
	public ProductSearchResult() {
		super("product");
		this.product = new Product();
	}

	/**
	 * Instantiates a new product search result.
	 *
	 * @param product the product
	 */
	public ProductSearchResult(Product product) {
		super("product");
		this.product = product;
	}

	/**
	 * Gets the product.
	 *
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}

	/**
	 * Sets the product.
	 *
	 * @param product the new product
	 */
	public void setProduct(Product product) {
		this.product = product;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductSearchResult [product=" + product + ", type=" + getType() + "]";
	}
	
}
