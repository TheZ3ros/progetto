package com.example.progetto.bean;
public class UserBean {
    private String username;
    private String password;

    public UserBean(String username, String password){
        this.username=username;
        this.password=password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public void setUsername(String user){
        username=user;
    }
    public void setPassword(String pass){
        password=pass;
    }
}

