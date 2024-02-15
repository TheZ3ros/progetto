package com.example.progetto.model;

public class Agency {

    private String username;
    private String password;

    public Agency(){

        username =null;
        password=null;
    }
    public void setUser(String user){
        username =user;
    }
    public void setPassword(String pass){
        password=pass;
    }
    public String getPassword(){
        return password;
    }
    public String getUsername(){
        return username;
    }
}
