package com.ispw.progetto.bean;

public class TripStatusBean {
    private final boolean status;
    private final String username;

    public TripStatusBean(String username, boolean status){
        this.username = username;
        this.status=status;
    }

    public String getUsername(){return username;}
    public boolean isStatus(){return status;}

}
