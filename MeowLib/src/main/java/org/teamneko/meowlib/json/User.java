package org.teamneko.meowlib.json;

import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class User.
 */
@XmlRootElement
public class User {
    
    /** The first name. */
    private String firstName;
    
    /** The id. */
    private long id;
    
    /** The last name. */
    private String lastName;    
    
    /** The number. */
    private String number;    
    
    /** The type. */
    private String type;
    
    /**
     * Instantiates a new user.
     */
    public User() {
        id = -1;
        firstName = "";
        lastName = "";
        number = "";
        type = "";
    }

	/**
	 * Instantiates a new user.
	 *
	 * @param id the id
	 * @param firstName the first name
	 * @param lastName the last name
	 * @param number the number
	 * @param type the type
	 */
	public User(long id, String firstName, String lastName, String number, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.type = type;
    }
     
    /**
     * Gets the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }
     
    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }
 
    /**
     * Gets the number.
     *
     * @return the number
     */
    public String getNumber() {
        return number;
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
     * Sets the first name.
     *
     * @param firstName the new first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(long id) {
        this.id = id;
    }
    
    /**
     * Sets the last name.
     *
     * @param lastName the new last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Sets the number.
     *
     * @param number the new number
     */
    public void setNumber(String number) {
        this.number = number;
    }

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", number=" + number
				+ ", type=" + type + "]";
	}
}