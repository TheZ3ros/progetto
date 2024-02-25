package com.ispw.progetto.model;

import com.ispw.progetto.pattern.decorator.UserTripDecorator;
import com.ispw.progetto.pattern.decorator.UserTripInterface;

public class TripStatus extends UserTripDecorator {
    private final boolean status;

    public TripStatus(UserTripInterface userTrip, boolean status){
        super(userTrip);
        this.status=status;
    }


    public boolean isStatus(){return status;}
}
