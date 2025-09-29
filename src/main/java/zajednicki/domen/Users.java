/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zajednicki.domen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Marija
 */
public class Users implements Serializable {
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private ArrayList<Entries> entries;
    private ArrayList<Groups> groups;

    public Users() {
    }

    public Users(long userID, String firstName, String lastName, String email, String password, ArrayList<Entries> entries, ArrayList<Groups> groups) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.entries = entries;
        this.groups = groups;
    }


    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

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
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users other = (Users) obj;
        return this.userID == other.userID;
    }

    public ArrayList<Entries> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entries> entries) {
        this.entries = entries;
    }

    public ArrayList<Groups> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Groups> groups) {
        this.groups = groups;
    }

}
