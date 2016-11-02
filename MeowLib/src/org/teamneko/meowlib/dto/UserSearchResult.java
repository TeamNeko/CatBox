package org.teamneko.meowlib.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserSearchResult extends SearchResult {
	private User user;

	public UserSearchResult() {
		super("user");
		this.user = new User();
	}

	public UserSearchResult(User user) {
		super("user");
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
