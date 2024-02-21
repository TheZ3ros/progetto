package com.ispw.progetto.model;

import com.ispw.progetto.pattern.decorator.UserTripDecorator;
import com.ispw.progetto.pattern.decorator.UserTripInterface;

public class TripStatus extends UserTripDecorator {
    private final boolean status;

    public TripStatus(UserTripInterface userTrip, boolean status){
        super(userTrip);
        this.status=status;
    }

    @Override
    public String getUsername(){
        return super.getUsername();
    }

    @Override
    public void setUsername(String username){
        super.setUsername(username);
    }

    public boolean isStatus(){return status;}
}
