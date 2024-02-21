package com.example.progetto.pattern.decorator;

import com.example.progetto.model.UserTrip;

public abstract class UserTripDecorator implements  UserTripInterface{
    protected UserTripInterface userTrip;
    public UserTripDecorator(UserTripInterface userTrip){
        this.userTrip=userTrip;
    }
}
