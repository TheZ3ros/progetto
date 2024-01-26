package com.example.progetto.bean;

public class AgencyBean {
    private final String username;
    private final String password;
    private boolean token;

    public AgencyBean(String username, String password){
        this.username=username;
        this.password=password;
        token=false;
    }
    public String getUsername(){

        return username;
    }
    public String getPassword(){

        return password;
    }
    public void setToken(){
        token=true;
    }
    public boolean getToken(){
        return token;
    }
}
