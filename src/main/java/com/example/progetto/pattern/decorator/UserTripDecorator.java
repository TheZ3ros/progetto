package com.example.progetto.pattern.decorator;


public abstract class UserTripDecorator implements  UserTripInterface{
    protected UserTripInterface userTrip;
    public UserTripDecorator(UserTripInterface userTrip){
        this.userTrip=userTrip;
    }
}
