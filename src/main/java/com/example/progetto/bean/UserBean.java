package com.example.progetto.bean;

import com.example.progetto.pattern.factory.BeanFactory;

public class UserBean implements BeanFactory {
    private String username;
    private String password;

@Override
    public String getUsername(){

        return username;
    }
    @Override
    public String getPassword(){

        return password;
    }
    @Override

    public void setPassword(String password) {
        this.password = password;
    }
    @Override

    public void setUsername(String username) {
        this.username = username;
    }


}

