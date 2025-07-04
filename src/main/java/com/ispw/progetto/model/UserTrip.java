package com.ispw.progetto.model;

import com.ispw.progetto.pattern.decorator.UserTripDecorator;
import com.ispw.progetto.pattern.decorator.UserTripInterface;

public class UserTrip extends UserTripDecorator {
    private final int idTrip;

    public UserTrip(UserTripInterface userTrip,int id) {
        super(userTrip);
        this.idTrip=id;
    }

    public int getIdTrip() {
        return idTrip;
    }

}
