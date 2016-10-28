package frontend.catbox.teamneko.org;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return number;
    }
    public void setEmail(String email) {
        this.number = email;
    }
     
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
     
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
 
     
    private long id;
    private String firstName;
    private String lastName;
    private String number;
         
}