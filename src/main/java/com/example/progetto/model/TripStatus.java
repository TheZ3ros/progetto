package com.example.progetto.model;

public class TripStatus {
    private boolean status;
    private String username;

    public TripStatus(String username, boolean status){
        this.username = username;
        this.status=status;
    }

    public String getUsername(){return username;}
    public boolean isStatus(){return status;}
}
