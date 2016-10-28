package org.teamneko.schrodinger.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    private String firstName;
    
    private long id;
    
    private String lastName;
    
    private String number;
    
    public User() {
        id = -1;
        firstName = "";
        lastName = "";
        number = "";
    }
    
    public User(long id, String firstName, String lastName, String number) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
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
         
}