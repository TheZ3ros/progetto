package com.example.progetto.bean;

public class TripStatusBean {
    private boolean status;
    private String username;

    public TripStatusBean(String username, boolean status){
        this.username = username;
        this.status=status;
    }

    public String getUsername(){return username;}
    public boolean isStatus(){return status;}

}
