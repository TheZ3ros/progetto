package com.example.progetto.entity;

public class User {
    private int idUser;
    private String Username;
    private String password;


    public void setUser(String user){
            Username=user;
    }
    public void setPassword(String pass){
        password=pass;
    }
    public String getPassword(){
        return password;
    }
    public String getUsername(){
        return Username;
    }




}
