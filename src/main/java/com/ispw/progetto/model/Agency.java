package com.ispw.progetto.model;

import com.ispw.progetto.pattern.factory.EntityFactory;

public class Agency implements EntityFactory {
    private String password;

    private String username;


    @Override
    public void setPassword(String pass){
        password=pass;
    }
    @Override
    public void setUsername(String user){
        username =user;
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
