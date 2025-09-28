/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Marija
 */
public class Entries implements Serializable{
    private long entryID;
    private Groups groups;
    private Users users;
    private String title;
    private String username;
    private String password;
    private String url;
    private String note;
    private Date createdAt;
    private Date updateAt;

    public Entries() {
    }

    public Entries(long entryID, Groups groups, Users users, String title, String username, String password, String url, String note, Date createdAt, Date updateAt) {
        this.entryID = entryID;
        this.groups = groups;
        this.users = users;
        this.title = title;
        this.username = username;
        this.password = password;
        this.url = url;
        this.note = note;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
    }

    
    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public long getEntryID() {
        return entryID;
    }

    public void setEntryID(long entryID) {
        this.entryID = entryID;
    }

    public Groups getGroups() {
        return groups;
    }

    public void setGroups(Groups groups) {
        this.groups = groups;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return title;
    }
    
    
    
    
}
