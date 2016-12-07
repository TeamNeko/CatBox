package org.teamneko.meowlib.json;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class SearchResult.
 */
@XmlRootElement
public class SearchResult {
	
	/** The type. */
	private String type;

	/**
	 * Instantiates a new search result.
	 */
	public SearchResult() {
		
	}
	
	/**
	 * Instantiates a new search result.
	 *
	 * @param type the type
	 */
	public SearchResult(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
}
