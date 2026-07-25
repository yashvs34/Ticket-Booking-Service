package org.example.entities;

import java.util.List;

public class User {
    private String userId;
    private String name;
    private String password;
    private boolean isAdmin;
    private List<String> bookedTickets;

    public User(){

    }

    public User(String userId, String name, String password, boolean isAdmin, List<String> bookedTickets) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.isAdmin = isAdmin;
        this.bookedTickets = bookedTickets;
    }

    public String getUserId() {return userId;}
    public String getName() {return name;}
    public String getPassword() {return password;}
    public boolean getIsAdmin() {return isAdmin;}
    public List<String> getBookedTickets() {return bookedTickets;}

    public void setUserId(String userId) {this.userId = userId;}
    public void setName(String name) {this.name = name;}
    public void setPassword(String password) {this.password = password;}
    public void setIsAdmin(boolean isAdmin) {this.isAdmin = isAdmin;}
    public void setBookedTickets(List<String> bookedTickets) {this.bookedTickets = bookedTickets;}
}
