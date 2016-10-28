package org.teamneko.meowlib.obj;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    private String firstName;
    
    private long id;
    
    private String lastName;
    
    private String number;
    
    private String type;
    
    public User() {
        id = -1;
        firstName = "";
        lastName = "";
        number = "";
        type = "";
    }
    
    public User(long id, String firstName, String lastName, String number, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.type = type;
    }
     
    public String getFirstName() {
        return firstName;
    }
    
    public long getId() {
        return id;
    }
     
    public String getLastName() {
        return lastName;
    }
 
    public String getNumber() {
        return number;
    }
    
    public String getType() {
		return type;
	}
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }

	public void setType(String type) {
		this.type = type;
	}     
}