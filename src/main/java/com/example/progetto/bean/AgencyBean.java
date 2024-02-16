package com.example.progetto.bean;

public class AgencyBean {
    private final String username;
    private final String password;

    public AgencyBean(String username, String password){
        this.username=username;
        this.password=password;
    }
    public String getUsername(){

        return username;
    }
    public String getPassword(){

        return password;
    }
}
