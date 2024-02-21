package com.example.progetto.model;

import com.example.progetto.pattern.decorator.UserTripInterface;

public class UserTrip implements UserTripInterface {
    private int idTrip;
    private String username;
    public void setUsername(String username){
        this.username=username;
    }
    public void setIdTrip(int idTrip){
        this.idTrip=idTrip;

    }
    public int getIdTrip(){
        return idTrip;
    }
    public String getUsername(){
        return username;
    }
}
