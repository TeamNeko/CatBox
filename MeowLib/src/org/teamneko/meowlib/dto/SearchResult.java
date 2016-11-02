package org.teamneko.meowlib.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SearchResult {
	private String type;

	public SearchResult() {
		
	}
	
	public SearchResult(String type) {
		this.type = type;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
