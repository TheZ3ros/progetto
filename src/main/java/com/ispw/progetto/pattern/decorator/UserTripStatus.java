package com.ispw.progetto.pattern.decorator;

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
