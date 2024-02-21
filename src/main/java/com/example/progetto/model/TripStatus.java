package com.example.progetto.model;

import com.example.progetto.pattern.decorator.UserTripInterface;

public class TripStatus implements UserTripInterface {
    private final boolean status;
    private final String username;

    public TripStatus(String username, boolean status){
        this.username = username;
        this.status=status;
    }

    public String getUsername(){return username;}
    public boolean isStatus(){return status;}
}
