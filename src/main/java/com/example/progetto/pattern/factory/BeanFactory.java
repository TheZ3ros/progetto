package com.example.progetto.pattern.factory;

public interface BeanFactory {
    String getUsername();
    String getPassword();
    void setPassword(String password);
    void setUsername(String username);
}
