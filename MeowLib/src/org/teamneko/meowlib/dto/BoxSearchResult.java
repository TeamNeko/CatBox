package org.teamneko.meowlib.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BoxSearchResult extends SearchResult {
	private Box box;

	public BoxSearchResult() {
		super("box");
		this.box = new Box();
	}
	
	public BoxSearchResult(Box box) {
		super("box");
		this.box = box;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}
	
}
