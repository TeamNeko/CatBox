package org.teamneko.meowlib.json;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class BoxSearchResult.
 */
@XmlRootElement
public class BoxSearchResult extends SearchResult {
	
	/** The box. */
	private Box box;

	/**
	 * Instantiates a new box search result.
	 */
	public BoxSearchResult() {
		super("box");
		this.box = new Box();
	}
	
	/**
	 * Instantiates a new box search result.
	 *
	 * @param box the box
	 */
	public BoxSearchResult(Box box) {
		super("box");
		this.box = box;
	}

	/**
	 * Gets the box.
	 *
	 * @return the box
	 */
	public Box getBox() {
		return box;
	}

	/**
	 * Sets the box.
	 *
	 * @param box the new box
	 */
	public void setBox(Box box) {
		this.box = box;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BoxSearchResult [box=" + box + "]";
	}
	
}
