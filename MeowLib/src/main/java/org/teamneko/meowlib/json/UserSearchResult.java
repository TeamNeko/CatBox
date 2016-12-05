package org.teamneko.meowlib.json;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class UserSearchResult.
 */
@XmlRootElement
public class UserSearchResult extends SearchResult {
	
	/** The user. */
	private User user;

	/**
	 * Instantiates a new user search result.
	 */
	public UserSearchResult() {
		super("user");
		this.user = new User();
	}

	/**
	 * Instantiates a new user search result.
	 *
	 * @param user the user
	 */
	public UserSearchResult(User user) {
		super("user");
		this.user = user;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/* 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UserSearchResult [user=" + user + "]";
	}
}
