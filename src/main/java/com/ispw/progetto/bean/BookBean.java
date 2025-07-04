package com.ispw.progetto.bean;

public class BookBean {
private String username;
private final int tripId;
public BookBean(String username, int id){
    this.tripId =id;
    this.username=username;
}

    public String getUsername() {
        return username;
    }

    public int getTripId() {
        return tripId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
