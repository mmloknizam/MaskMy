/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Marija
 */
public class Groups implements Serializable{
    private long groupID;
    private Users users;
    private String name;
    private ArrayList<Entries> entries;

    public Groups() {
    }

    public Groups(long groupID, Users users, String name, ArrayList<Entries> entries) {
        this.groupID = groupID;
        this.users = users;
        this.name = name;
        this.entries = entries;
    }




    public ArrayList<Entries> getEntries() {
        return entries;
    }

    public void setEntries(ArrayList<Entries> entries) {
        this.entries = entries;
    }

    public long getGroupID() {
        return groupID;
    }

    public void setGroupID(long groupID) {
        this.groupID = groupID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    
}
