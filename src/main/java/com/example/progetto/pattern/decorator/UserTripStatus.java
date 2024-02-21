package com.example.progetto.pattern.decorator;

import com.example.progetto.pattern.decorator.UserTripInterface;

public class UserTripStatus implements UserTripInterface {

    String username;

    public UserTripStatus(String username){
        this.setUsername(username);
    }

    @Override
    public void setUsername(String username) {
        this.username=username;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
