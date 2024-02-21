package com.example.progetto.model;

import com.example.progetto.pattern.factory.EntityFactory;

public class Agency implements EntityFactory {

    private String username;
    private String password;


    @Override
    public void setUsername(String user){
        username =user;
    }
    @Override
    public void setPassword(String pass){
        password=pass;
    }
    @Override
    public String getPassword(){
        return password;
    }
    @Override
    public String getUsername(){
        return username;
    }
}
