package com.example.progetto.bean;

public class BookBean {
private String username;
private int trip_id;
public BookBean(String username, int id){
    this.trip_id=id;
    this.username=username;
}

    public String getUsername() {
        return username;
    }

    public int getTripId() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
