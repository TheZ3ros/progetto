package com.ispw.progetto.bean;

public class BookBean {
private String username;
private int tripId;
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

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
